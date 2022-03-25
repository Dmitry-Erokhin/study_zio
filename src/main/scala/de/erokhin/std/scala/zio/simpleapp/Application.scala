package de.erokhin.std.scala.zio.simpleapp

import de.erokhin.std.scala.zio.simpleapp.config.HardCodedConfig
import de.erokhin.std.scala.zio.simpleapp.processor.{ Processor, SimpleProcessor }
import zio.Clock.*
import zio.Console.printLine
import zio.{ durationInt, Clock, Console, ZIO, ZIOAppDefault }

object Application extends ZIOAppDefault:

  private val program = for
    p           <- ZIO.service[Processor]
    _           <- printLine("App with layers, services etc")
    _           <- sleep(1.second)
    msg: String <- p.process
    _           <- printLine(msg)
  yield ()

  override def run: ZIO[Any, Any, Any] = program
    .provide(
      Clock.live,
      Console.live,
      HardCodedConfig.layer,
      SimpleProcessor.layer
    )
    .exitCode
