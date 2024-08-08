package web

import org.scalatra.ScalatraServlet
import org.scalatra.LifeCycle
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext): Unit = {
    context.mount(new PatientController, "/*")
  }
}
