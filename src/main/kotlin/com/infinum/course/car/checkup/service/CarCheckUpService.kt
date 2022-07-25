package com.infinum.course.car.checkup.service

import com.infinum.course.car.checkup.entities.carEntities.CarClientSide
import com.infinum.course.car.checkup.entities.CarDetails
import com.infinum.course.car.checkup.entities.checkupEntities.CarCheckUp
import com.infinum.course.car.checkup.repository.CarRepository
import com.infinum.course.car.checkup.repository.CheckUpRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.YEARS
import java.util.UUID


@Service
class CarCheckUpService (
   private val carRepository: CarRepository,
   private val checkUpRepository: CheckUpRepository
        ) {

    fun addCar(clientCar : CarClientSide) : UUID =
        carRepository.save(
            clientCar.toCar(),
        ).id


    fun addCheckup(carCheckUp: CarCheckUp) : UUID =
        checkUpRepository.save(
            carCheckUp
        ).id

    fun getCarDetails(id : UUID) : CarDetails {
        val car = carRepository.getCarById(id)
        return CarDetails(
            car,
            car.checkUps.isEmpty() || YEARS.between(LocalDateTime.now(),LocalDateTime.parse(car.checkUps.elementAt(0).performedAt)).toInt() !== 0
        )
    }

    fun getCheckupAnalytics() : Map<String,Long> =
        checkUpRepository.getCheckupAnalytics().map { it.split(",") }.associate { it[0] to it[1].toLong() }

    fun getAllCars(pageable: Pageable) =
        carRepository.findAll(pageable)


    fun getCheckupsById(
        checkedCarId: UUID,
        page: Int,
        size: Int,
    ) : Page<CarCheckUp> {
        return checkUpRepository.findAllByCheckedCarId(
            checkedCarId,
            PageRequest.of(page,size)
        )
    }





}