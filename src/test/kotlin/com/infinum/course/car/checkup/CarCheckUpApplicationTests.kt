package com.infinum.course.car.checkup

import com.infinum.course.car.checkup.entities.Car
import com.infinum.course.car.checkup.entities.CarCHeckUpInvalidException
import com.infinum.course.car.checkup.entities.CarCheckUp
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
class CarCheckUpApplicationTests {

    private  var carCheckUpRepository: InMemoryCarCheckUpRepository = InMemoryCarCheckUpRepository()

    @Test
    fun returnCorrectCar () {
        val car = Car(LocalDateTime.now(),2014,"nissan","skyline","FSHDSF56")
        val id = carCheckUpRepository.addCar(car)
        assertThat(carCheckUpRepository.fetchCarDetails(id)[0] == car)
    }

    @Test
    fun doesAddCheckupThrowError() {
        val nonExistentId = -1L
        val falseCheckup = CarCheckUp(LocalDateTime.now(),"Test",1L,nonExistentId)
        assertThatThrownBy {carCheckUpRepository.addCheckup(falseCheckup)}.isInstanceOf(CarCHeckUpInvalidException::class.java)
    }



}
