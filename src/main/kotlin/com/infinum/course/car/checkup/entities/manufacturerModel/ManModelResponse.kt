package com.infinum.course.car.checkup.entities.manufacturerModel

import com.fasterxml.jackson.annotation.JsonProperty

data class ManModelResponse (
    @JsonProperty("cars") val manModels : List<ManModel>
        ) {
    fun toObjects() : List<ManufacturerModel> {
        val manufacturersWithModels = mutableListOf<ManufacturerModel>()
        for (manModel in manModels) {
            for (model in manModel.models) {
                manufacturersWithModels.add(
                    ManufacturerModel(
                        manModel.manufacturer,
                        model
                    )
                )
            }
        }
        return manufacturersWithModels
    }
}

data class ManModel (
    @JsonProperty("manufacturer") val manufacturer : String,
    @JsonProperty("models") val models : List<String>
        )