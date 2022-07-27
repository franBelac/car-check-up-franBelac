package com.infinum.course.car.checkup.controller

import com.infinum.course.car.checkup.entities.carEntities.CarClientSide
import com.infinum.course.car.checkup.entities.checkupEntities.CarCheckUp
import com.infinum.course.car.checkup.entities.CarDetails
import com.infinum.course.car.checkup.service.CarCheckUpService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

import java.util.UUID

@Controller
class CarCheckUpController(private val carCheckUpService: CarCheckUpService) {

    @PostMapping("/car")
    @ResponseBody
    fun addCar(@RequestBody clientCar: CarClientSide): ResponseEntity<UUID> = ResponseEntity(
        carCheckUpService.addCar(clientCar), HttpStatus.CREATED
    )

    @PostMapping("/checkup")
    @ResponseBody
    fun addCheckup(@RequestBody carCheckUp: CarCheckUp): ResponseEntity<UUID> = ResponseEntity(
        carCheckUpService.addCheckup(carCheckUp), HttpStatus.CREATED
    )


    @GetMapping("/car-details/{id}")
    @ResponseBody
    fun getCarDetails(@PathVariable id: UUID): ResponseEntity<CarDetails> = ResponseEntity.ok(
        carCheckUpService.getCarDetails(id)
    )

    @GetMapping("/checkup-analytics")
    @ResponseBody
    fun getCheckupAnalytics(): ResponseEntity<Map<String, Long>> = ResponseEntity.ok(
        carCheckUpService.getCheckupAnalytics()
    )

    @GetMapping("/paged/cars")
    fun getAllCars(pageable: Pageable) = ResponseEntity.ok(
        carCheckUpService.getAllCars(pageable)
    )

    @GetMapping("/paged/checkups")
    fun getCheckupsForId(
        @RequestParam(defaultValue = "") checkedCarId: UUID,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "2") size: Int,
    ) =
        ResponseEntity.ok(
            carCheckUpService.getCheckupsById(checkedCarId, page, size)
        )

}