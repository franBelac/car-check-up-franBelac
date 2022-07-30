package com.infinum.course.car.checkup.entities.carEntities

import com.infinum.course.car.checkup.entities.checkupEntities.CarCheckUp
import com.infinum.course.car.checkup.entities.manufacturerModel.ManufacturerModel
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "cars")
class Car(

    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "date_added")
    val dateAdded: String,

    @Column(name = "production_year")
    val productionYear: Short,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns(
        value = [
            JoinColumn(
                name = "manufacturer",
                referencedColumnName = "manufacturer"
            ),
            JoinColumn(
                name = "model",
                referencedColumnName = "model"
            )
        ]
    )
    val manufacturerModel: ManufacturerModel,

    @Column(name = "vin")
    val vin: String,

    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "checked_car_id")
    @OrderBy("performedAt DESC")
    val checkUps: MutableSet<CarCheckUp> = mutableSetOf()
)
