package com.infinum.course.car.checkup.entities

data class CarDetails (
    val car: CarClientSide,
    val checkups: List<CarCheckUp>,
    val checkupNecessary : String
        )