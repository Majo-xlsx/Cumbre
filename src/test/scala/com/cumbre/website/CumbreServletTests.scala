package com.cumbre.website

import org.scalatra.test.scalatest._

class CumbreServletTests extends ScalatraFunSuite {

  addServlet(classOf[CumbreServlet], "/*")

  test("GET / on CumbreServlet should return status 200") {
    get("/") {
      status should equal (200)
    }
  }

}
