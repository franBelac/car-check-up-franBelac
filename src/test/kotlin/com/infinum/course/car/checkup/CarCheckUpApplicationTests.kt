package com.infinum.course.car.checkup

import com.infinum.course.car.checkup.entities.carEntities.Car
import com.infinum.course.car.checkup.entities.checkupEntities.CarCheckUp
import com.infinum.course.car.checkup.entities.manufacturerModel.ManufacturerModel
import com.infinum.course.car.checkup.repository.CarRepository
import com.infinum.course.car.checkup.repository.CheckUpRepository
import com.infinum.course.car.checkup.repository.ManufacturerModelRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import java.util.UUID

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CarCheckUpApplicationTests @Autowired constructor(
    val carRepository: CarRepository,
    val checkUpRepository: CheckUpRepository,
    val manufacturerModelRepository: ManufacturerModelRepository
) {

    private val listOfIds = mutableListOf<Car>()

    private lateinit var returnedUUID: UUID

    @BeforeEach
    fun setup() {

        manufacturerModelRepository.saveAll(
            listOf(
                ManufacturerModel("Porsche", "Panamera"),
                ManufacturerModel("Hyundai", "i10"),
                ManufacturerModel("Volkswagen", "Polo"),
                ManufacturerModel("Citroen", "C3")
            )
        )

        carRepository.deleteAll()
        checkUpRepository.deleteAll()
        listOfIds.clear()
        val car1 = Car(
            dateAdded = "2021-07-23T02:04:38.344699600",
            productionYear = 2003,
            manufacturerModel = ManufacturerModel(
                "Porsche",
                "Panamera"
            ),
            vin = "HVUDIS215"
        )
        val car1Id = carRepository.save(car1)
        val car2 = Car(
            dateAdded = "2020-07-23T02:04:38.344699600",
            productionYear = 2018,
            manufacturerModel = ManufacturerModel(
                "Hyundai",
                "i10"
            ),
            vin = "OIJWF89"
        )
        val car2Id = carRepository.save(car2)
        val car3 = Car(
            dateAdded = "2017-07-23T02:04:38.344699600",
            productionYear = 2017,
            manufacturerModel = ManufacturerModel(
                "Volkswagen",
                "Polo"
            ),
            vin = "DIOJSF54"
        )
        val car3Id = carRepository.save(car3)
        val car4 = Car(
            dateAdded = "2015-07-23T02:04:38.344699600",
            productionYear = 2016,
            manufacturerModel = ManufacturerModel(
                "Citroen",
                "C3"
            ),
            vin = "ASFHOIJA648"
        )
        val car4Id = carRepository.save(car4)
        listOfIds.addAll(
            listOf(
                car1Id,
                car2Id,
                car3Id,
                car4Id
            )
        )

        val checkup1 = CarCheckUp(
            performedAt = "2015-07-23T02:04:38.344699600",
            workerName = "Pablo",
            price = 300,
            checkedCarId = car1Id.id
        )
        checkUpRepository.save(checkup1)

        val checkup2 = CarCheckUp(
            performedAt = "2022-07-23T02:04:38.344699600",
            workerName = "John",
            price = 400,
            checkedCarId = car1Id.id
        )
        checkUpRepository.save(checkup2)

        val checkup3 = CarCheckUp(
            performedAt = "2020-07-23T02:04:38.344699600",
            workerName = "Rob",
            price = 150,
            checkedCarId = car2Id.id
        )

        checkUpRepository.save(checkup3)

        val checkup4 = CarCheckUp(
            performedAt = "2019-07-23T02:04:38.344699600",
            workerName = "Martyn",
            price = 200,
            checkedCarId = car2Id.id
        )

        checkUpRepository.save(checkup4)

        returnedUUID = car1Id.id
    }

    @Test
    fun testGettingAnalytics() {
        assertThat(
            checkUpRepository.getCheckupAnalytics().size
        ).isEqualTo(4)
    }

    @Test
    fun checkForConstraints() {
        assertThatThrownBy {
            carRepository.save(
                Car(
                    dateAdded = "",
                    productionYear = 2016,
                    manufacturerModel = ManufacturerModel(
                        "Lamborghini",
                        "Sian"
                    ),
                    vin = "DHS541"
                )
            )
        }.isInstanceOf(JpaObjectRetrievalFailureException::class.java)
    }

    @Test
    fun checkDeletion() {
        assertThat(
            carRepository.deleteById(returnedUUID)
        ).isInstanceOf(Unit::class.java)
    }

}
