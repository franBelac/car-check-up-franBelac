package com.infinum.course.car.checkup.config

import com.infinum.course.car.checkup.entities.carEntities.CarNotFoundException
import com.infinum.course.car.checkup.entities.checkupEntities.CarCHeckUpInvalidException
import com.infinum.course.car.checkup.entities.manufacturerModel.ManufacturerModelException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(CarNotFoundException::class)
    fun handleNoCar(e: CarNotFoundException) = ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(CarCHeckUpInvalidException::class)
    fun handleInvalidCheckUp(e: CarCHeckUpInvalidException) = ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(ManufacturerModelException::class)
    fun handleManModelException(e: ManufacturerModelException) = ResponseEntity(e.message, HttpStatus.NOT_ACCEPTABLE)

    @ExceptionHandler(value = [(Exception::class)])
    fun handleException(e: Exception): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }


}