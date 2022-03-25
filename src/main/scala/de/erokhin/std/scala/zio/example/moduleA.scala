package de.erokhin.std.scala.zio.example

import zio.*

object moduleA:
  type ModuleA = ModuleA.Service

  object ModuleA:
    trait Service:
      def letsGoA(v: Int): UIO[String]

    // val any: ZLayer[ModuleA, Nothing, ModuleA] =
    //   ZLayer.requires[ModuleA]

    val live: ZLayer[Any, Nothing, Service] = ZLayer.succeed {
      new Service:
        def letsGoA(v: Int): UIO[String] = UIO(s"done: v = $v")
    }

  def letsGoA(v: Int): URIO[ModuleA, String] = ZIO.serviceWithZIO(_.letsGoA(v))
