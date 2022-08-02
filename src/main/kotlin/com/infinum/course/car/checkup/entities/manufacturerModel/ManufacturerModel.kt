package com.infinum.course.car.checkup.entities.manufacturerModel

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

@Entity
@IdClass(ManufacturerModelId::class)
@Table(name = "manufacturermodels")
data class ManufacturerModel(
    @Id
    @Column(name = "manufacturer")
    val manufacturer: String,

    @Id
    @Column(name = "model")
    val model: String
)