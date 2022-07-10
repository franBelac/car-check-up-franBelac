package com.infinum.course.car.checkup

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.io.Resource

fun main() {
    // Entry point
    val applicationContext: ApplicationContext = AnnotationConfigApplicationContext(ApplicationConfiguration::class.java)
    val checkupRepo = applicationContext.getBean(CarCheckUpRepository::class.java)
//    checkupRepo.insert(LocalDateTime.now(),Car(manufacturer = "Porsche", model = "Taycan", vin = "123" ))
//    checkupRepo.insert(LocalDateTime.now(),Car(manufacturer = "Skoda", model = "Octavia", vin = "456" ))
//    checkupRepo.insert(LocalDateTime.now(),Car(manufacturer = "Fiat", model = "Seicento", vin = "789" ))
    println(checkupRepo.findById(1))
}

@Configuration
@PropertySource("classpath:application.properties")
class ApplicationConfiguration {

    @Value("\${user-repository.database.name}")
    lateinit var dbName: String

    @Value("\${user-repository.database.user}")
    lateinit var user: String

    @Value("\${user-repository.database.password}")
    lateinit var password: String

    @Value("file:src/main/resources/\${user-repository.database.name}.txt")
    lateinit var carRepositoryResource: Resource

    @Value("\${carcheckuprepository.switch}")
    lateinit var switch: String

    @Bean
    fun dataSource(): DataSource = DataSource(
        dbName = dbName,
        username = user,
        password = password
    )

    @Bean
    fun carCheckupRepository(): CarCheckUpRepository =
        if (switch == "turned-on") InMemoryCarCheckUpRepository(dataSource()) else InFileCarCheckUpRepository(carRepositoryResource)
}
