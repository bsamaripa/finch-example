package com.bsamaripa

import cats.instances.int._
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await
import io.finch._
import io.finch.syntax._

object Hello extends App {

	val port = "8081"

  def division: Endpoint[Int] = post("div" :: path[Int] :: path[Int]) { (a: Int, b: Int) =>
    Ok(a / b)
  }

  def addition: Endpoint[Int] = post("add" :: path[Int] :: path[Int]) { (a: Int, b: Int) =>
    Ok(a + b)
  }

	val api: Service[Request, Response] = (division :+: addition)
		.handle {
      case e: ArithmeticException => BadRequest(e)
    }.toServiceAs[Text.Plain]

  Await.ready(Http.server.serve(s":$port", api))
}
