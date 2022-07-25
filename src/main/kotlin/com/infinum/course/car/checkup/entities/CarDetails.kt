package com.infinum.course.car.checkup.entities

import com.infinum.course.car.checkup.entities.carEntities.Car

data class CarDetails (
    val car: Car,
    val checkupNecessary : Boolean
        )