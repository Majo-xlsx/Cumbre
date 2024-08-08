package web

import org.scalatra._
import org.scalatra.Handler
import services.GoogleSheetsService
import io.circe.generic.auto._
import io.circe.parser._

case class Patient(id: String, fullName: String, idNumber: String, birthDate: String, gender: String, address: String, phone: String, email: String, registrationDate: String)

class PatientController extends ScalatraServlet with Handler {
  get("/") {
    contentType = "text/html"
    scala.io.Source.fromResource("web/index.html").mkString
  }

  post("/addPatient") {
    val jsonString = request.body
    val patient = decode[Patient](jsonString).getOrElse(Patient("", "", "", "", "", "", "", "", ""))
    
    GoogleSheetsService.appendData("Pacientes", List(patient.id, patient.fullName, patient.idNumber, patient.birthDate, patient.gender, patient.address, patient.phone, patient.email, patient.registrationDate))
    
    Ok("Paciente agregado")
  }
}
