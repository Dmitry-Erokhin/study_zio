package de.erokhin.std.scala.zio

import de.erokhin.std.scala.zio.Config.Data
import zio.*

trait Config:
  def data: ZIO[Any, Nothing, Data]

object Config:

  case class Data(i: Int, s: String)

  def data: ZIO[Config, Nothing, Data] = ZIO.serviceWithZIO(_.data)

final class HardCodedConfig extends Config:
  def data: ZIO[Any, Nothing, Data] = ZIO.succeed(Data(42, "forty two"))

object HardCodedConfig:
  val layer: ZLayer[Any, Nothing, Config] = ZLayer.succeed(new HardCodedConfig)
