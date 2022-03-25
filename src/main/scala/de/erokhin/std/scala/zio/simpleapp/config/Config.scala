package de.erokhin.std.scala.zio.simpleapp.config

import zio.*

trait Config:
  def data: ZIO[Any, Nothing, Data]
