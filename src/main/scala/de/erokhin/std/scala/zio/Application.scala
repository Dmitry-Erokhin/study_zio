package de.erokhin.std.scala.zio

import zio.*
import zio.Clock.*
import zio.Console.*

object Application extends ZIOAppDefault:

  val env = Clock.live ++ Console.live ++ (HardCodedConfig.layer >>> SimpleProcessor.layer)

  val program = for
    p           <- ZIO.service[Processor]
    _           <- printLine("App with layers, services etc")
    _           <- sleep(1.second)
    msg: String <- p.process
    _           <- printLine(msg)
  yield ()

  override def run: ZIO[Any, Any, Any] = program.provide(env).exitCode
