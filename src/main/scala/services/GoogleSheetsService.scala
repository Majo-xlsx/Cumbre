package services

import sttp.client3._
import sttp.model.Uri
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.auto._

object GoogleSheetsService {
  private val apiUrl = uri"https://script.google.com/macros/s/AKfycbwcS6pt8tkxYkTJE8DStTZHi67Uh5BuB0Zu_id2bbKVHvNzuhfCnFxBuEHLyBGaR4Otcw/exec" // URL de tu API web

  case class Patient(id: String, fullName: String, idNumber: String, birthDate: String, gender: String, address: String, phone: String, email: String, registrationDate: String)

  def getPatients(): List[List[String]] = {
    val backend = HttpURLConnectionBackend()
    val response = basicRequest.get(apiUrl).send(backend)
    val body = response.body.getOrElse("[]")
    decode[List[List[String]]](body).getOrElse(List.empty)
  }

  def writeData(sheetName: String, range: String, values: List[List[String]]): Unit = {
    val backend = HttpURLConnectionBackend()
    val body = new ValueRange().setValues(values.map(_.asJava).asJava)
    val response = basicRequest
      .post(apiUrl)
      .body(body.asJson.noSpaces)
      .send(backend)
    
    if (response.isFailure) {
      println(s"Error escribiendo datos: ${response.statusCode} - ${response.body}")
    }
  }

  def appendData(sheetName: String, values: List[String]): Unit = {
    val backend = HttpURLConnectionBackend()
    val response = basicRequest
      .post(apiUrl)
      .body(values.asJson.noSpaces)
      .send(backend)

    if (response.isFailure) {
      println(s"Error añadiendo datos: ${response.statusCode} - ${response.body}")
    }
  }

  def main(args: Array[String]): Unit = {
    // Leer datos
    val patients = getPatients()
    println(s"Pacientes: $patients")

    // Añadir un nuevo paciente
    val newPatient = Patient("3", "Carlos Gómez", "12345679", "03/03/1990", "M", "Calle Ejemplo 123", "555-5557", "carlos.gomez@example.com", "03/03/2020")
    appendData("Pacientes", List(newPatient.id, newPatient.fullName, newPatient.idNumber, newPatient.birthDate, newPatient.gender, newPatient.address, newPatient.phone, newPatient.email, newPatient.registrationDate))
  }
}

