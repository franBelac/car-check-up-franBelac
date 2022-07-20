package com.infinum.course.car.checkup

import com.infinum.course.car.checkup.entities.Car
import com.infinum.course.car.checkup.entities.CarCHeckUpInvalidException
import com.infinum.course.car.checkup.entities.CarCheckUp
import com.infinum.course.car.checkup.entities.CarNotFoundException
import com.infinum.course.car.checkup.repository.CarCheckUpRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.context.annotation.ComponentScan
import java.time.LocalDateTime

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan
class CarCheckUpApplicationTests constructor (
    @Autowired  var carCheckUpRepository: CarCheckUpRepository
        ) {

    @Test
    fun testAddCar() {
        assertThat(
            carCheckUpRepository.addCar(
                Car(
                    LocalDateTime.now(),
                    2013,
                    "Hyundai",
                    "i10",
                    "WIOSEF89"
                )
            )
        ).isEqualTo(1)
    }

    @Test
    fun testFetchingDetails() {
        val id : Long = -1L
        assertThatThrownBy { carCheckUpRepository.fetchCarDetails(id) }.isInstanceOf(CarNotFoundException::class.java).hasMessage("Car with id $id not found")
    }

    @Test
    fun testAddingCheckups() {
        assertThatThrownBy { carCheckUpRepository.addCheckup(
            CarCheckUp(
                LocalDateTime.now(),
                "Pablo",
                87,
                -1
            )
        ) }.isInstanceOf(CarCHeckUpInvalidException::class.java)
    }

}
