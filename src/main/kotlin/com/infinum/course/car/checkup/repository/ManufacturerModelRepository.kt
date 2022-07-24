package com.infinum.course.car.checkup.repository

import com.infinum.course.car.checkup.entities.manufacturerModel.ManufacturerModel
import com.infinum.course.car.checkup.entities.manufacturerModel.ManufacturerModelId
import org.springframework.data.jpa.repository.JpaRepository

interface ManufacturerModelRepository : JpaRepository<ManufacturerModel,ManufacturerModelId> {

}