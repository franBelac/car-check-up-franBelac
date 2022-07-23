package com.infinum.course.car.checkup

import com.infinum.course.car.checkup.entities.carEntities.Car
import com.infinum.course.car.checkup.entities.checkupEntities.CarCheckUp
import com.infinum.course.car.checkup.repository.CarRepository
import com.infinum.course.car.checkup.repository.CheckUpRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CarCheckUpApplicationTests @Autowired constructor (
    val carRepository: CarRepository,
    val checkUpRepository: CheckUpRepository
        ) {

    private val listOfIds = mutableListOf<Car>()

    @BeforeEach
    fun setup() {
        carRepository.deleteAll()
        checkUpRepository.deleteAll()
        listOfIds.clear()
        val car1 = Car(
            dateAdded = "2021-07-23T02:04:38.344699600",
            productionYear = 2003,
            manufacturer = "Porsche",
            model = "Taycan",
            vin = "HVUDIS215"
        )
        val car1Id = carRepository.save(car1)
        val car2 = Car(
            dateAdded = "2020-07-23T02:04:38.344699600",
            productionYear = 2018,
            manufacturer = "Hyundai",
            model = "Tucson",
            vin = "OIJWF89"
        )
        val car2Id = carRepository.save(car2)
        val car3 = Car(
            dateAdded = "2017-07-23T02:04:38.344699600",
            productionYear = 2017,
            manufacturer = "Kia",
            model = "Stinger",
            vin = "DIOJSF54"
        )
        val car3Id = carRepository.save(car3)
        val car4 = Car(
            dateAdded = "2015-07-23T02:04:38.344699600",
            productionYear = 2016,
            manufacturer = "Opel",
            model = "Insignia",
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

    }

    @Test
    fun testGettingAnalytics() {
        assertThat(
            checkUpRepository.getCheckupAnalytics().size
        ).isEqualTo(4)
    }

}
