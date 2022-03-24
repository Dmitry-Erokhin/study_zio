package de.erokhin.std.scala.zio.example

import de.erokhin.std.scala.zio.example.moduleA.ModuleA
import de.erokhin.std.scala.zio.example.moduleB.ModuleB.Service
import de.erokhin.std.scala.zio.example.moduleB.{ letsGoB, ModuleB }
import zio.Clock.sleep
import zio.Console.printLine
import zio.*

import java.io.IOException

object Application extends zio.App:

  val env: ZLayer[Any, Nothing, zio.Console & Clock & Service] = Console.live ++ Clock.live ++ (ModuleA.live >>> ModuleB.live)

  val program: ZIO[Console with Clock with ModuleB, IOException, Unit] =
    for
      _ <- printLine(s"Welcome to ZIO!")
      _ <- sleep(1.second)
      r <- letsGoB(10)
      _ <- printLine(r)
    yield ()

  def run(args: List[String]) =
    program.provide(env).exitCode
