package com.infinum.course.car.checkup.entities.manufacturerModel

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

@Entity
@IdClass(ManufacturerModelId::class)
@Table(name = "manufacturermodels")
class ManufacturerModel() {

    @Id
    @Column(name = "manufacturer")
    var manufacturer: String = ""

    @Id
    @Column(name = "model")
    var model: String = ""

    constructor(
        manufacturer: String,
        model: String
    ) : this() {
        this.manufacturer = manufacturer
        this.model = model
    }

    override fun equals(other: Any?): Boolean {
        return other is ManufacturerModel
                && this.manufacturer == other.manufacturer
                && this.model == other.model
    }

    override fun toString(): String {
        return manufacturer + model
    }
}