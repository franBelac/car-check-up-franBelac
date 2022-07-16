package com.infinum.course.car.checkup.entities

data class CarClientSide (
    val productionYear: Short,
    val manufacturer: String,
    val model: String,
    val vin: String
        )