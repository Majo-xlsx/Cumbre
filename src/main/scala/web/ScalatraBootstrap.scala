package web

import org.scalatra.LifeCycle
import org.scalatra.ScalatraServlet
import javax.servlet.ServletContext
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}

object ScalatraBootstrap {
  def main(args: Array[String]): Unit = {
    println("Iniciando aplicación...")
    val server = new Server(8080) // Configura el puerto 8080
    val context = new ServletContextHandler(ServletContextHandler.SESSIONS)
    context.setContextPath("/")
    
    val servletHolder = new ServletHolder(classOf[ScalatraServlet])
    context.addServlet(servletHolder, "/*")
    
    server.setHandler(context)
    
    server.start()
    server.join()
  }
}

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext): Unit = {
    context.mount(new PatientController, "/*")
  }
}
