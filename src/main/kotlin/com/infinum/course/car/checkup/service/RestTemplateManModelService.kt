package com.infinum.course.car.checkup.service

import com.infinum.course.car.checkup.entities.manufacturerModel.ManModelResponse
import com.infinum.course.car.checkup.entities.manufacturerModel.ManufacturerModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class RestTemplateManModelService(
    private val restTemplate: RestTemplate,
    @Value("\${manufacturer-model.base-url}")  private val baseUrl : String
) {

    fun getManufacturerModels() : List<ManufacturerModel> {
        return restTemplate
            .getForObject<ManModelResponse>(baseUrl)
            .toObjects()
    }

}