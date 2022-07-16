package com.infinum.course.car.checkup.entities

class CarNotFoundException(id: String = "Car with given id not found") : Exception("Car with vin $id not found")
