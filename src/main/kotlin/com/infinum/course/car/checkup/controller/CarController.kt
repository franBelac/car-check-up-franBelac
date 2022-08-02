package com.infinum.course.car.checkup.controller

import com.infinum.course.car.checkup.controller.dto.CarDetailsResource
import com.infinum.course.car.checkup.controller.dto.CarDetailsResourceAssembler
import com.infinum.course.car.checkup.controller.dto.CarResource
import com.infinum.course.car.checkup.controller.dto.CarResourceAssembler
import com.infinum.course.car.checkup.entities.carEntities.CarClientSide
import com.infinum.course.car.checkup.entities.carEntities.Car
import com.infinum.course.car.checkup.service.CarCheckUpService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.PagedModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

import java.util.UUID

@Controller
class CarController(
    private val carCheckUpService: CarCheckUpService,
    private val carResourceAssembler: CarResourceAssembler,
    private val carDetailsResourceAssembler: CarDetailsResourceAssembler
) {

    @PostMapping("/car")
    @ResponseBody
    fun addCar(@RequestBody clientCar: CarClientSide): ResponseEntity<UUID> = ResponseEntity(
        carCheckUpService.addCar(clientCar), HttpStatus.CREATED
    )

    @GetMapping("/car/{id}")
    @ResponseBody
    fun getCarDetails(@PathVariable id: UUID): ResponseEntity<CarDetailsResource> = ResponseEntity.ok(
        carDetailsResourceAssembler.toModel(
            carCheckUpService.getCarDetails(id)
        )
    )

    @GetMapping("/cars")
    fun getAllCars(
        pageable: Pageable,
        pagedResourcesAssembler: PagedResourcesAssembler<Car>
    ): ResponseEntity<PagedModel<CarResource>> = ResponseEntity.ok(
        pagedResourcesAssembler.toModel(
            carCheckUpService.getAllCars(pageable),
            carResourceAssembler
        )
    )

    @DeleteMapping("/car/{id}")
    @ResponseBody
    fun deleteCar(
        @PathVariable id: UUID
    ): ResponseEntity<String> {
        carCheckUpService.deleteCar(id)
        return ResponseEntity.ok("$id DELETED")
    }

}