package com.infinum.course.car.checkup.config

import com.infinum.course.car.checkup.service.CarCheckUpService
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import java.util.concurrent.TimeUnit.DAYS

@Configuration
@EnableScheduling
class SchedulerConfiguration(
    private val carCheckUpService: CarCheckUpService
) : SchedulingConfigurer {
    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
        taskRegistrar.addFixedDelayTask( { carCheckUpService.addManufacturerModels() }, DAYS.toMillis(1)  )
    }
}