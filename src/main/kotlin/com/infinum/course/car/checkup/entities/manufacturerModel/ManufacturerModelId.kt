package com.infinum.course.car.checkup.entities.manufacturerModel

class ManufacturerModelId  (
    val manufacturer: String = "UNKNOWN",
    val model : String = "UNKNOWN"
) : java.io.Serializable {

    override fun equals(other: Any?): Boolean =
        (other is ManufacturerModelId) &&
                this.manufacturer == other.manufacturer &&
                this.model == other.model
}
