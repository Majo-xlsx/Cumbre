package web

import org.scalatra.LifeCycle
import org.scalatra.ScalatraServlet
import javax.servlet.ServletContext

object ScalatraBootstrap {
  def main(args: Array[String]) {
  }
}

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context mount new PatientController with Handler
  }
}
