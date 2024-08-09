package com.cumbre.website.models

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

final case class Patient(
    id: String,
    fullName: String,
    idNumber: String,
    birthDate: String,
    gender: String,
    address: String,
    phone: String,
    email: String,
    registrationDate: String,
)

object Patient:
  given Decoder[Patient] = deriveDecoder[Patient]
