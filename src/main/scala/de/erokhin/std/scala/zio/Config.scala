package de.erokhin.std.scala.zio

import de.erokhin.std.scala.zio.Config.Data
import zio.{ ZIO, ZLayer }

trait Config:
  def data: ZIO[Any, Nothing, Data]

object Config:

  case class Data(i: Int, s: String)

  private class HardCoded extends Config:
    def data: ZIO[Any, Nothing, Data] = ZIO.succeed(Data(42, "forty two"))

  // ================== ACCESSORS ==================

  def data: ZIO[Config, Nothing, Data] = ZIO.serviceWithZIO(_.data)

  // ================== LAYERS ==================

  val hardCoded: ZLayer[Any, Nothing, Config] = ZLayer.succeed(new HardCoded)
