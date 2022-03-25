package de.erokhin.std.scala.zio.simpleapp.processor

import de.erokhin.std.scala.zio.simpleapp.config.{ Config, Data }
import de.erokhin.std.scala.zio.simpleapp.processor.{ Processor, SimpleProcessor }
import zio.*

final class SimpleProcessor(config: Config) extends Processor:
  def process: ZIO[Any, Nothing, String] = for
    data: Data <- config.data
    message     = s"Purum-purum-pu: ${data.i} ----> ${data.s}"
  yield message

object SimpleProcessor:
  val layer: URLayer[Config, SimpleProcessor] = (new SimpleProcessor(_)).toLayer
