package com.infinum.course.car.checkup

import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CarCheckUpApplicationTests {

    private val dataSource = mockk<DataSource>()

    lateinit var inMemoryCarCheckUpRepository: InMemoryCarCheckUpRepository

    @BeforeEach
    fun setUp() {
        inMemoryCarCheckUpRepository = InMemoryCarCheckUpRepository(dataSource)
    }

    @Test
    fun returnsCorrectCar() {
        val localDateTime = LocalDateTime.now()
        val  car1 = Car("Skoda","Fabia","100")
        inMemoryCarCheckUpRepository.insert(localDateTime,car1)

        assertThat(inMemoryCarCheckUpRepository.findById(1).car).isEqualTo(car1)

    }
}
