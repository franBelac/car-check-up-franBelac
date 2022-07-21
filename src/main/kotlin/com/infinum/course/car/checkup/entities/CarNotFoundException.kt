package com.infinum.course.car.checkup.entities

class CarNotFoundException(id: Long) : Exception("Car with id $id not found")
