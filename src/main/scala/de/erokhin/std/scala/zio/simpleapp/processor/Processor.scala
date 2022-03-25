package de.erokhin.std.scala.zio.simpleapp.processor

import zio.ZIO

trait Processor:
  def process: ZIO[Any, Nothing, String]
