package services

import sttp.client3._
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.auto._

object GoogleSheetsService {
  private val apiUrl = uri"https://script.google.com/macros/s/AKfycbwcS6pt8tkxYkTJE8DStTZHi67Uh5BuB0Zu_id2bbKVHvNzuhfCnFxBuEHLyBGaR4Otcw/exec"

  case class Patient(id: String, fullName: String, idNumber: String, birthDate: String, gender: String, address: String, phone: String, email: String, registrationDate: String)

  def getPatients(): List[List[String]] = {
    val backend = HttpURLConnectionBackend()
    val response = basicRequest.get(apiUrl).send(backend)
    val body = response.body.getOrElse("[]")
    decode[List[List[String]]](body).getOrElse(List.empty)
  }

  def writeData(sheetName: String, values: List[List[String]]): Unit = {
    val backend = HttpURLConnectionBackend()
    val body = values.asJson.noSpaces // Convierte directamente la lista a JSON
    val response = basicRequest
      .post(apiUrl)
      .body(body)
      .send(backend)
    
    response.body match {
      case Right(_) => println("Datos escritos exitosamente")
      case Left(error) => println(s"Error escribiendo datos: $error")
    }
  }

  def appendData(sheetName: String, values: List[String]): Unit = {
    val backend = HttpURLConnectionBackend()
    val response = basicRequest
      .post(apiUrl)
      .body(values.asJson.noSpaces)
      .send(backend)

    response.body match {
      case Right(_) => println("Datos añadidos exitosamente")
      case Left(error) => println(s"Error añadiendo datos: $error")
    }
  }
}