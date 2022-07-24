package com.infinum.course.car.checkup.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestConfiguration {

    @Bean
    fun provideRestTemplate(
        restTemplateBuilder: RestTemplateBuilder,
        @Value("\${manufacturer-model.base-url}") baseUrl : String
    ) : RestTemplate =
        restTemplateBuilder.rootUri(baseUrl).build()
}