package com.infinum.course.car.checkup.entities.carEntities

class CarNotFoundException(id: Long) : Exception("Car with id $id not found")
