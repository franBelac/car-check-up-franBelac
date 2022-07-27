package com.infinum.course.car.checkup.entities.manufacturerModel

import com.fasterxml.jackson.annotation.JsonProperty

data class ManModelResponse(
    @JsonProperty("cars") val manModels: List<ManModel>
) {
    fun toObjects(): List<ManufacturerModel> =
        manModels.flatMap { manModel ->
            manModel.models.map { model ->
                ManufacturerModel(
                    manModel.manufacturer,
                    model
                )
            }
        }

}

data class ManModel(
    @JsonProperty("manufacturer") val manufacturer: String,
    @JsonProperty("models") val models: List<String>
)