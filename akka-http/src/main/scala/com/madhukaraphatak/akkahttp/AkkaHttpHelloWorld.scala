package com.madhukaraphatak.akkahttp


import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._

/**
  * Created by madhu on 8/11/15.
  */
object AkkaHttpHelloWorld {

  def main(args: Array[String]) {

    implicit val actorSystem = ActorSystem("system")
    implicit val actorMaterializer = ActorMaterializer()


    val route =
      pathSingleSlash {
        get {
          complete {
            "Hello world"
          }
        }
      }
    Http().bindAndHandle(route,"localhost",8080)

    println("server started at 8080")
  }
}
