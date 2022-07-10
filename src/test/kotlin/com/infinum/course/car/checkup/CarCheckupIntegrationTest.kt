package com.infinum.course.car.checkup

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [ApplicationConfiguration::class])
class CarCheckupIntegrationTest @Autowired constructor (
    private var applicationContext: ApplicationContext,
) {

    @Test
    fun verifyCheckupRepoBean() {
        assertThat(applicationContext.getBean(DataSource::class.java)).isNotNull
        assertThat(applicationContext.getBean(CarCheckUpRepository::class.java)).isNotNull


        assertThatThrownBy { applicationContext.getBean("random bean") }
            .isInstanceOf(NoSuchBeanDefinitionException::class.java)

    }

    @Test
    fun getCheckupById() {
        val localDateTime = LocalDateTime.now()
        val car = Car("Porsche","Taycan","988")
        val checkUpRepository = applicationContext.getBean<CarCheckUpRepository>()
        checkUpRepository.insert(localDateTime,car)
        assertThat(checkUpRepository.findById(1).car).isEqualTo(car)
    }
}


