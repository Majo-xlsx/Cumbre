package com.cumbre.website

import com.cumbre.website.models.Patient
import io.circe.parser.decode
import majo.cumbre.services.GoogleSheetsService
import org.scalatra.*

class CumbreServlet extends ScalatraServlet:

  get("/") {
    views.html.patientForm()

  }

  post("/patients") {
    val jsonString = request.body
    val patient    = decode[Patient](jsonString).getOrElse(Patient("", "", "", "", "", "", "", "", ""))

    GoogleSheetsService.appendData(
      "Pacientes",
      List(
        patient.id,
        patient.fullName,
        patient.idNumber,
        patient.birthDate,
        patient.gender,
        patient.address,
        patient.phone,
        patient.email,
        patient.registrationDate,
      ),
    )

    Ok("Paciente agregado")
  }
end CumbreServlet
