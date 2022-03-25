package de.erokhin.std.scala.zio

import zio.*

trait Processor:
  def process: ZIO[Any, Nothing, String]

object Processor:

  class Simple(config: Config) extends Processor:
    def process: ZIO[Any, Nothing, String] = for
      data: Config.Data <- config.data
      message            = s"Purum-purum-pu: ${data.i} ----> ${data.s}"
    yield message

  // ================== ACCESSORS ==================

  def process: ZIO[Processor, Nothing, String] = ZIO.serviceWithZIO(_.process)

  // ================== LAYERS ==================

  val live: URLayer[Config, Simple] = (new Simple(_)).toLayer
