package com.infinum.course.car.checkup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class CarCheckUpApplication

fun main(args: Array<String>) {
    runApplication<CarCheckUpApplication>(*args)
}