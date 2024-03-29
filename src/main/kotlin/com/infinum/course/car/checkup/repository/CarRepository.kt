package com.infinum.course.car.checkup.repository

import com.infinum.course.car.checkup.entities.carEntities.Car
import org.springframework.data.jpa.repository.JpaRepository

import java.util.UUID

interface CarRepository : JpaRepository<Car, UUID> {

    fun save(car: Car): Car

    fun getCarById(uuid: UUID): Car

}