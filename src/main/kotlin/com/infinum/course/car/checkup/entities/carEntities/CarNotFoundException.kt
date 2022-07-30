package com.infinum.course.car.checkup.entities.carEntities

import java.util.*

class CarNotFoundException(id: UUID) : Exception("Car with id $id not found")
