package de.erokhin.std.scala.zio

import zio.{ ZIO, ZLayer }

object Config:

  case class Data(i: Int, s: String)

  trait Service:
    def data: ZIO[Any, Nothing, Data]

  private class HardCoded extends Service:
    def data: ZIO[Any, Nothing, Data] = ZIO.succeed(Data(42, "forty two"))

  // ================== ACCESSORS ==================

  def data: ZIO[Config.Service, Nothing, Data] = ZIO.serviceWithZIO(_.data)

  // ================== LAYERS ==================

  val hardCoded: ZLayer[Any, Nothing, Config.Service] = ZLayer.succeed(new HardCoded)
