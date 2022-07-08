package com.infinum.course.car.checkup

class CarNotFoundException(vin : String = "Car with given vin not found") : Exception("Car with vin $vin not found")