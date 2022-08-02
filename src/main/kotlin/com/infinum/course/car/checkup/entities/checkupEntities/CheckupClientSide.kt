package com.infinum.course.car.checkup.entities.checkupEntities

import java.util.UUID

data class CheckupClientSide(
    val performedAt: String,
    val workerName: String,
    val price: Long,
    val checkedCarId: UUID
) {
    fun toCheckup(): CarCheckUp =
        CarCheckUp(
            performedAt = performedAt,
            workerName = workerName,
            price = price,
            checkedCarId = checkedCarId
        )
}