package com.infinum.course.car.checkup

import java.time.LocalDateTime
import  java.time.temporal.ChronoUnit.YEARS

object CarCheckUpSystem {
    private val carsList = listOf<Car>(
        Car(manufacturer = "Porsche", model = "Taycan", vin = "1"),
        Car(manufacturer = "Volkswagen", model = "Golf", vin = "2"),
        Car(manufacturer = "Skoda", model = "Octavia", vin = "3"),
        Car(manufacturer = "Ford", model = "Fiesta", vin = "4")
    )
    private val checkUpList = mutableListOf<CheckUp>(
        CheckUp(LocalDateTime.of(2018,1,1,2,30), carsList[0]),
        CheckUp(LocalDateTime.of(2019,2,2,3,45), carsList[1]),
        CheckUp(LocalDateTime.of(2020,3,3,4,0), carsList[2]),
        CheckUp(LocalDateTime.of(2020,4,4,4,30), carsList[3]),
        CheckUp(LocalDateTime.of(2022,5,5,5,0), carsList[0]),
        CheckUp(LocalDateTime.of(2022,6,6,5,30), carsList[1]),
        CheckUp(LocalDateTime.of(2021,7,7,6,40), carsList[2]),
        CheckUp(LocalDateTime.of(2020,8,8,7,0), carsList[3]),
        CheckUp(LocalDateTime.of(2019,9,9,7,30), carsList[0]),
        CheckUp(LocalDateTime.of(2018,10,10,8,0), carsList[1])
    )

    fun isCheckupNecessary (vin : String) : Boolean {

        val currTime = LocalDateTime.now()

        return checkUpList
            .filter { it.car.vin == vin }
            .any { YEARS.between(it.performedAt,currTime).toInt() == 0 }
    }

    fun addCheckUp(vin : String) : CheckUp {
        val filteredCar = carsList.firstOrNull { it.vin == vin } ?: throw CarNotFoundException(vin)
        val newCheckUp = CheckUp(LocalDateTime.now(),filteredCar)
        checkUpList.add(newCheckUp)
        return newCheckUp
    }

    fun getCheckUps (vin: String) : List<CheckUp> {
        val filteredCheckups = checkUpList.filter { it.car.vin == vin }
        if (filteredCheckups.isEmpty()) throw CarNotFoundException(vin)
        return filteredCheckups
    }

    fun countCheckUps (manufacturer : String) : Int {
        return checkUpList.count { it.car.manufacturer == manufacturer }
    }


}