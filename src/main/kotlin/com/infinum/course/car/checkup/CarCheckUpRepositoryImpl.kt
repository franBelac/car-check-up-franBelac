package com.infinum.course.car.checkup

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.YEARS

@Service
class InMemoryCarCheckUpRepository() {

    private val carCheckUpMap = mutableMapOf<Long, CarCheckUp>()
    private val carsMap = mutableMapOf<Long, Car>()

    init {
        val localDateTime = LocalDateTime.now()
        carsMap.putAll(
            mapOf(
                1L to Car(localDateTime,2000,"Porsche","917","1KFUE"),
                2L to Car(localDateTime,2001,"Skoda","Octavia","OIJIO2"),
                3L to Car(localDateTime,2002,"Seat","Leon","VBUUD43")
        )
        )
        carCheckUpMap.putAll(mapOf(
            1L to CarCheckUp(LocalDateTime.of(2017, 1, 14, 10, 34),"Carlos",300L,1L),
            2L to CarCheckUp(LocalDateTime.of(2022, 1, 14, 10, 34),"Santos",175L,2L),
            3L to CarCheckUp(LocalDateTime.of(2020, 1, 14, 10, 34),"Santos",175L,3L),
            4L to CarCheckUp(LocalDateTime.of(2018, 1, 14, 10, 34),"Santos",175L,3L)
        ))
    }

    fun addCar(car: Car) : Long {
        val id = (carsMap.keys.maxOrNull() ?: 0) + 1
        carsMap[id] = car
        return id
    }

    fun fetchCarDetails(id: Long) : Array<Any> {
        val car = carsMap[id] ?: throw CarNotFoundException()
        val checkUps = carCheckUpMap.filter { it.value.carId == id }.values.sortedByDescending { it.performedAt }
        val localDateTime = LocalDateTime.now()
        val noCheckupNeeded = checkUps.any { YEARS.between(it.performedAt,localDateTime) == 0L }
        val checkupNecessary = if(noCheckupNeeded)  "This car doesn't need a checkup" else "This car needs a checkup"
        return arrayOf(car,checkUps,checkupNecessary)
    }


    fun addCheckup(carCheckUp: CarCheckUp): Long {
        val newCheckUpId = carCheckUp.carId
        println(carsMap.keys.firstOrNull { it == newCheckUpId } )
        carsMap.keys.firstOrNull { it == newCheckUpId }  ?: throw CarCHeckUpInvalidException()
        val id = (carCheckUpMap.keys.maxOrNull() ?: 0) + 1
        carCheckUpMap[id] = carCheckUp
        return id
    }

    fun getCheckupAnalytics() : Map<String,Int> {
        val mapOfAnalytics = mutableMapOf<String,Int>()
        val idSFromCheckups = carCheckUpMap.values.map { it.carId }

        for ((carId,car) in carsMap) {

            val manufacturer = car.manufacturer

            for (idFromCheckup in idSFromCheckups) {

                if (idFromCheckup == carId) {

                        if (mapOfAnalytics[manufacturer] == null) mapOfAnalytics[manufacturer] = 0

                        mapOfAnalytics[manufacturer]?.let { mapOfAnalytics.put(manufacturer, it + 1) }
                }
            }
        }
        return mapOfAnalytics
    }
}
