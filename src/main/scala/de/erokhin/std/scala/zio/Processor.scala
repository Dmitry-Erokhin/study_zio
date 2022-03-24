package de.erokhin.std.scala.zio

import zio.{ ZIO, ZLayer }

object Processor:

  trait Service:
    def process: ZIO[Any, Nothing, String]

  class Simple(config: Config.Service) extends Service:
    def process: ZIO[Any, Nothing, String] = for
      data: Config.Data <- config.data
      message            = s"Purum-purum-pu: ${data.i} ----> ${data.s}"
    yield message

  // ================== ACCESSORS ==================

  def process: ZIO[Processor.Service, Nothing, String] = ZIO.serviceWithZIO(_.process)

  // ================== LAYERS ==================

  val live: ZLayer[Config.Service, Nothing, Service] =
    ZLayer.fromService((config: Config.Service) => new Simple(config))
