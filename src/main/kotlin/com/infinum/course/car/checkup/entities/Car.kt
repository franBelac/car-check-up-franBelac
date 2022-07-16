package com.infinum.course.car.checkup.entities

import java.time.LocalDateTime

data class Car(
    val dateAdded: LocalDateTime,
    val productionYear: Short,
    val manufacturer: String,
    val model: String,
    val vin: String
)
