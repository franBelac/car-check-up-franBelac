package com.infinum.course.car.checkup.entities.checkupEntities

import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "carcheckups")
data class CarCheckUp(

    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "performed_at")
    val performedAt: String = "" ,

    @Column(name = "worker_name")
    val workerName: String = "Johnny",

    @Column(name = "price")
    val price: Long = 300L,

    @Column(name = "checked_car_id")
    val checkedCarId: UUID = UUID.randomUUID()
)