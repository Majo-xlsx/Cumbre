package web

import org.scalatra.LifeCycle
import org.scalatra.ScalatraServlet
import javax.servlet.ServletContext

object ScalatraBootstrap {
  def main(args: Array[String]): Unit = {
    println("Iniciando aplicación...")
  }
}

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext): Unit = {
    context.mount(new PatientController, "/*")
  }
}