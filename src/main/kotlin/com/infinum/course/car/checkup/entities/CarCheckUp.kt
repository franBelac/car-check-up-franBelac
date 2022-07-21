package com.infinum.course.car.checkup.entities

import java.time.LocalDateTime

data class CarCheckUp(
    val performedAt: LocalDateTime,
    val workerName: String,
    val price: Long,
    val carId: Long,
)