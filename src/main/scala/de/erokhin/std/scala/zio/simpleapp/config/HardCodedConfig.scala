package de.erokhin.std.scala.zio.simpleapp.config

import zio.*
import de.erokhin.std.scala.zio.simpleapp.config.Config
import de.erokhin.std.scala.zio.simpleapp.config.Data

final class HardCodedConfig extends Config:
  def data: ZIO[Any, Nothing, Data] = ZIO.succeed(Data(42, "forty two"))

object HardCodedConfig:
  val layer: ZLayer[Any, Nothing, Config] = ZLayer.succeed(new HardCodedConfig)
