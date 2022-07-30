package com.infinum.course.car.checkup.service

import com.infinum.course.car.checkup.entities.carEntities.CarClientSide
import com.infinum.course.car.checkup.entities.carEntities.CarDetails
import com.infinum.course.car.checkup.entities.checkupEntities.CarCheckUp
import com.infinum.course.car.checkup.entities.checkupEntities.CheckupAnalytics
import com.infinum.course.car.checkup.entities.checkupEntities.CheckupClientSide
import com.infinum.course.car.checkup.entities.manufacturerModel.ManufacturerModel
import com.infinum.course.car.checkup.repository.CarRepository
import com.infinum.course.car.checkup.repository.CheckUpRepository
import com.infinum.course.car.checkup.repository.ManufacturerModelRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.YEARS
import java.util.UUID


@Service
class CarCheckUpService(
    private val carRepository: CarRepository,
    private val checkUpRepository: CheckUpRepository,
    private val manufacturerModelRepository: ManufacturerModelRepository,
    private val restTemplateManModelService: RestTemplateManModelService,
    private val cacheService: CacheService
) {

    fun addCar(clientCar: CarClientSide): UUID {

        val manufacturerModel = ManufacturerModel(clientCar.manufacturer, clientCar.model)

        cacheService.getManModel(manufacturerModel)

        return carRepository.save(
            clientCar.toCar(),
        ).id
    }

    fun addCheckup(checkupClientSide: CheckupClientSide): UUID {
        return checkUpRepository.save(
            checkupClientSide.toCheckup()
        ).id
    }

    fun getCarDetails(id: UUID): CarDetails {

        cacheService.evictManModel()

        val car = carRepository.getCarById(id)
        return CarDetails(
            car,
            car.checkUps.isEmpty() || YEARS.between(
                LocalDateTime.now(),
                LocalDateTime.parse(car.checkUps.elementAt(0).performedAt)
            ).toInt() != 0
        )
    }

    fun getCheckupAnalytics(): CheckupAnalytics =
        CheckupAnalytics(
            checkUpRepository.getCheckupAnalytics()
                .map { it.split(",") }
                .associate { it[0] to it[1].toLong() }
        )

    fun getAllCars(pageable: Pageable) =
        carRepository.findAll(pageable)


    fun getCheckupsById(
        checkedCarId: UUID,
        pageable: Pageable,
        sortBy: String
    ): Page<CarCheckUp> {
        return if (sortBy == "asc") checkUpRepository.findAllByCheckedCarIdOrderByPerformedAtAsc(
            checkedCarId,
            pageable
        ) else checkUpRepository.findAllByCheckedCarIdOrderByPerformedAtDesc(
            checkedCarId,
            pageable
        )
    }

    fun addManufacturerModels(): List<ManufacturerModel> {
        return manufacturerModelRepository.saveAll(
            restTemplateManModelService
                .getManufacturerModels()
        )
    }
}