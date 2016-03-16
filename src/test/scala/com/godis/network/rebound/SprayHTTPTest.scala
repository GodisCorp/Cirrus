package com.godis.network.rebound

import com.godis.network.rebound.core.{BasicClient, FailedRequest}

import scala.language.postfixOps

//import com.godis.network.rebound.Defaults._
import com.godis.network.rebound.Protocol._
import com.godis.network.rebound.client.SprayHTTP.POST

import scala.concurrent.Await
import scala.concurrent.duration._

object SprayHTTPTest extends App {

  implicit val client = BasicClient
    .Builder
    .withRequestBodyCharset("UTF-8")
    .withTweak(_.setConnectTimeout(0))
    .withTweak(_.setReadTimeout(0))
    .build()

  val user = User("James", "F", "+234808888330", "james@gmail.com")

  val post = POST[List[User]]("http://demo6556920.mockable.io/users")

  try {

    val response = Await.result(post ! user, 3 seconds)

    println(s"Content: ${response.body}")

  } catch {
    case e: FailedRequest => e.printStackTrace()
  }
}