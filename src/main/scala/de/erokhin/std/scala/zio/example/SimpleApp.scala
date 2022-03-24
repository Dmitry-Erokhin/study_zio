package de.erokhin.std.scala.zio.example

import zio.Console.printLine
import zio.ZIO

object SimpleApp extends zio.ZIOAppDefault:

  def run = for
    name <- ZIO.succeed("Dmitry")
    _    <- printLine(s"Hello, $name, welcome to ZIO!")
  yield ()
