package majo.cumbre.services

import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*
import sttp.client3.*

object GoogleSheetsService:
  private val apiUrl =
    uri"https://script.google.com/macros/s/AKfycbwcS6pt8tkxYkTJE8DStTZHi67Uh5BuB0Zu_id2bbKVHvNzuhfCnFxBuEHLyBGaR4Otcw/exec"

  def getPatients(): List[List[String]] =
    val backend  = HttpURLConnectionBackend()
    val response = basicRequest.get(apiUrl).send(backend)
    val body     = response.body.getOrElse("[]")
    decode[List[List[String]]](body).getOrElse(List.empty)

  def writeData(sheetName: String, values: List[List[String]]): Unit =
    val backend  = HttpURLConnectionBackend()
    val body     = values.asJson.noSpaces // Convierte directamente la lista a JSON
    val response = basicRequest
      .post(apiUrl)
      .body(body)
      .send(backend)

    response.body match
      case Right(_)    => println("Datos escritos exitosamente")
      case Left(error) => println(s"Error escribiendo datos: $error")
  end writeData

  def appendData(sheetName: String, values: List[String]): Unit =
    val backend  = HttpURLConnectionBackend()
    val response = basicRequest
      .post(apiUrl)
      .body(values.asJson.noSpaces)
      .send(backend)

    response.body match
      case Right(_)    => println("Datos añadidos exitosamente")
      case Left(error) => println(s"Error añadiendo datos: $error")
  end appendData
end GoogleSheetsService
