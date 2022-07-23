package com.infinum.course.car.checkup.entities.carEntities

import com.infinum.course.car.checkup.entities.checkupEntities.CarCheckUp
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "cars")
class Car(

    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "date_added")
    val dateAdded: String = "",

    @Column(name = "production_year")
    val productionYear: Short = 2000,

    @Column(name = "manufacturer")
    val manufacturer: String = "Porsche",

    @Column(name = "model")
    val model: String = "Taycan",

    @Column(name = "vin")
    val vin: String = "JHKSF51",

    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "checked_car_id")
    @OrderBy("performedAt DESC")
    val checkUps: MutableSet<CarCheckUp> = mutableSetOf()
)
