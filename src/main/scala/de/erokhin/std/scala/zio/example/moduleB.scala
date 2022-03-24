package de.erokhin.std.scala.zio.example

import de.erokhin.std.scala.zio.example.moduleA.ModuleA
import zio.*

object moduleB:
  type ModuleB = ModuleB.Service

  object ModuleB:
    trait Service:
      def letsGoB(v: Int): UIO[String]

    val live: ZLayer[ModuleA, Nothing, ModuleB] = ZLayer.fromService { (moduleA: ModuleA.Service) =>
      new Service:
        def letsGoB(v: Int): UIO[String] =
          moduleA.letsGoA(v)
    }

  def letsGoB(v: Int): URIO[ModuleB, String] =
    ZIO.serviceWithZIO(_.letsGoB(v))
