package com.infinum.course.car.checkup.service

import com.infinum.course.car.checkup.entities.manufacturerModel.ManModelDTO
import com.infinum.course.car.checkup.entities.manufacturerModel.ManufacturerModel
import com.infinum.course.car.checkup.entities.manufacturerModel.ManufacturerModelException
import com.infinum.course.car.checkup.repository.ManufacturerModelRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CacheService(
    private val manufacturerModelRepository: ManufacturerModelRepository
) {

    @Cacheable("manmodel")
    fun getManModel(manufacturerModel: ManufacturerModel): ManModelDTO {
        if (!manufacturerModelRepository.findAll().contains(
                manufacturerModel
            )
        ) throw ManufacturerModelException()

        return ManModelDTO(
            manufacturerModel.manufacturer,
            manufacturerModel.model
        )
    }

    @CacheEvict(value = arrayOf("manmodel"), allEntries = true)
    fun evictManModel() {
        println("Removing all from cache...")
    }


}