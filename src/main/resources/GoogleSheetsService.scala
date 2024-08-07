package services

import com.google.api.services.sheets.v4.{Sheets, SheetsScopes}
import com.google.api.services.sheets.v4.model.{ValueRange}
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.http.HttpCredentialsAdapter
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import java.io.FileInputStream
import scala.jdk.CollectionConverters._

object GoogleSheetsService {
  private val APPLICATION_NAME = "Proyecto"
  private val JSON_FACTORY = GsonFactory.getDefaultInstance
  private val SCOPES = List(SheetsScopes.SPREADSHEETS).asJava
  private val CREDENTIALS_FILE_PATH = "path/to/your/credentials.json" // Actualiza esta ruta con la ruta real de tu archivo credentials.json
  private val spreadsheetId = "1dzU4CQBC2BMpXPzuiyPdjMveRmkQwlG8mM8eaZCghpQ"  // ID de tu hoja de cálculo
  private val sheetsService = getSheetsService

  private def getSheetsService: Sheets = {
    val credentialsStream = new FileInputStream(CREDENTIALS_FILE_PATH)
    val credentials = GoogleCredentials.fromStream(credentialsStream).createScoped(SCOPES)
    new Sheets.Builder(new NetHttpTransport, JSON_FACTORY, new HttpCredentialsAdapter(credentials))
      .setApplicationName(APPLICATION_NAME)
      .build()
  }

  def readData(sheetName: String, range: String): List[List[String]] = {
    val response = sheetsService.spreadsheets().values().get(spreadsheetId, s"$sheetName!$range").execute()
    val values = response.getValues
    if (values == null || values.isEmpty) {
      List.empty
    } else {
      values.asScala.map(_.asScala.toList.map(_.toString)).toList
    }
  }

  def writeData(sheetName: String, range: String, values: List[List[String]]): Unit = {
    val body = new ValueRange().setValues(values.map(_.asJava).asJava)
    sheetsService.spreadsheets().values()
      .update(spreadsheetId, s"$sheetName!$range", body)
      .setValueInputOption("RAW")
      .execute()
  }

  def appendData(sheetName: String, values: List[String]): Unit = {
    val body = new ValueRange().setValues(List(values.asJava).asJava)
    sheetsService.spreadsheets().values()
      .append(spreadsheetId, s"$sheetName!A1", body)
      .setValueInputOption("RAW")
      .execute()
  }
}
