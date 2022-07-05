package com.infinum.course.car.checkup

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object CarCheckUpSystem {
    private val carsList = mutableListOf<Car>()
    private val checkUpList = mutableListOf<CheckUp>()

    init {
        carsList.addAll(
            listOf(
                Car(manufacturer = "Porsche", model = "Taycan", vin = "1"),
                Car(manufacturer = "Volkswagen", model = "Golf", vin = "2"),
                Car(manufacturer = "Skoda", model = "Octavia", vin = "3"),
                Car(manufacturer = "Ford", model = "Fiesta", vin = "4")
            )
        )
        checkUpList.addAll(
            listOf(
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
        )
    }

    fun isCheckupNecessary (vin : String) : Boolean {

        val filteredCheckups = checkUpList
            .filter { it.car.vin == vin }
            .filter { ChronoUnit.YEARS.between(it.performedAt,LocalDateTime.now()).toInt() == 0 }
        if (filteredCheckups.isEmpty()) return true
        return false
    }

    fun addCheckUp(vin : String) : CheckUp {
        val filteredCar = carsList.filter { it.vin == vin }
        if (filteredCar.isEmpty()) throw Exception("That car doesn't exist!")
        val newCheckUp = CheckUp(LocalDateTime.now(),filteredCar[0])
        checkUpList.add(newCheckUp)
        return newCheckUp
    }

    fun getCheckUps (vin: String) : List<CheckUp> {
        val filteredCheckups = checkUpList.filter { it.car.vin == vin }
        if (filteredCheckups.isEmpty()) throw Exception("No car with the given VIN!")
        return filteredCheckups
    }

    fun countCheckUps (manufacturer : String) : Int {
        return checkUpList.count { it.car.manufacturer == manufacturer }
    }


}