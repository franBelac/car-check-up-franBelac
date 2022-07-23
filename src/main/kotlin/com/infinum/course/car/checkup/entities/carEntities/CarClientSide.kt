package com.infinum.course.car.checkup.entities.carEntities

import java.time.LocalDateTime

class CarClientSide (
    val productionYear: Short,
    val manufacturer: String,
    val model: String,
    val vin: String
        ) {
    fun toCar() =
        Car (
            dateAdded = LocalDateTime.now().toString(),
            productionYear = productionYear,
            manufacturer = manufacturer,
            model = model,
            vin = vin
                )
}