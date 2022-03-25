package de.erokhin.std.scala.zio

import zio.Clock.sleep
import zio.Console.printLine
import zio.{ Clock, Console }
import zio.Clock.*
import zio.*
import zio.Clock.*
import zio.Console.*

object Application extends ZIOAppDefault:

  val env = Clock.live ++ Console.live ++ (Config.hardCoded >>> Processor.live)

  val program = for
    _           <- printLine("App with layers, services etc")
    _           <- sleep(1.second)
    msg: String <- Processor.process
    _           <- printLine(msg)
  yield ()

  override def run: ZIO[Any, Any, Any] = program.provide(env).exitCode
