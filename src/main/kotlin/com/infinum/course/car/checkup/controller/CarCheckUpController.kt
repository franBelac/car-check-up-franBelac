package com.infinum.course.car.checkup.controller

import com.infinum.course.car.checkup.repository.CarCheckUpRepository
import com.infinum.course.car.checkup.entities.CarClientSide
import com.infinum.course.car.checkup.entities.Car
import com.infinum.course.car.checkup.entities.CarCheckUp
import com.infinum.course.car.checkup.entities.CarDetails
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import java.time.LocalDateTime

@Controller
class CarCheckUpController(private val carCheckUpRepository: CarCheckUpRepository) {

    @PostMapping("/add-car")
    @ResponseBody
    fun addCar(@RequestBody clientCar: CarClientSide) : ResponseEntity<Long> {
        val car = Car(LocalDateTime.now(),clientCar.productionYear,clientCar.manufacturer,clientCar.model,clientCar.vin)
        val addedCarId = carCheckUpRepository.addCar(car)
        return ResponseEntity(addedCarId, HttpStatus.OK)
    }

    @PostMapping("/add-checkup")
    @ResponseBody
    fun addCheckup(@RequestBody carCheckUp: CarCheckUp) : ResponseEntity<Long> {
        val addedCheckUpId = carCheckUpRepository.addCheckup(carCheckUp)
        return ResponseEntity(addedCheckUpId,HttpStatus.OK)
    }


    @GetMapping("/car-details/{id}")
    @ResponseBody
    fun getCarDetails(@PathVariable id : Long) : ResponseEntity<CarDetails> {
        return ResponseEntity(carCheckUpRepository.fetchCarDetails(id),HttpStatus.OK)
    }

    @GetMapping("/checkup-analytics")
    @ResponseBody
    fun getCheckupAnalytics() : ResponseEntity<Map<String,Int>> {
        return ResponseEntity(carCheckUpRepository.getCheckupAnalytics(),HttpStatus.OK)
    }

    @ExceptionHandler(value = [(Exception::class)])
    fun handleException(e: Exception) : ResponseEntity<String> {
        return ResponseEntity(e.message,HttpStatus.NOT_FOUND)
    }

}