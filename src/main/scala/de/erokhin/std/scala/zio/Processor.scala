package de.erokhin.std.scala.zio

import zio.*

trait Processor:
  def process: ZIO[Any, Nothing, String]

final class SimpleProcessor(config: Config) extends Processor:
  def process: ZIO[Any, Nothing, String] = for
    data: Config.Data <- config.data
    message            = s"Purum-purum-pu: ${data.i} ----> ${data.s}"
  yield message

object SimpleProcessor:
  val layer: URLayer[Config, SimpleProcessor] = (new SimpleProcessor(_)).toLayer
