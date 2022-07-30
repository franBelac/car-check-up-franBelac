package com.infinum.course.car.checkup.controller.dto

import com.infinum.course.car.checkup.controller.CarController
import com.infinum.course.car.checkup.entities.carEntities.Car
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CarResourceAssembler : RepresentationModelAssemblerSupport<Car, CarResource>(
    CarController::class.java, CarResource::class.java
) {
    override fun toModel(entity: Car): CarResource {
        return createModelWithId(entity.id, entity).apply {
            add(
                linkTo<CarController> {
                    getCarDetails(entity.id)
                }.withRel("details")
            )

        }
    }

    override fun instantiateModel(entity: Car): CarResource {
        return CarResource(
            entity.id,
            entity.dateAdded,
            entity.productionYear,
            entity.manufacturerModel.manufacturer,
            entity.manufacturerModel.model,
            entity.vin
        )
    }
}

@Relation(collectionRelation = IanaLinkRelations.ITEM_VALUE)
data class CarResource(
    val id: UUID,
    val dateAdded: String,
    val productionYear: Short,
    val manufacturer: String,
    val model: String,
    val vin: String
) : RepresentationModel<CarResource>()