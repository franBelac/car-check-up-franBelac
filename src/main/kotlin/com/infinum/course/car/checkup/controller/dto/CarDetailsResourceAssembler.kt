package com.infinum.course.car.checkup.controller.dto

import com.infinum.course.car.checkup.controller.CarController
import com.infinum.course.car.checkup.controller.CheckupController
import com.infinum.course.car.checkup.entities.carEntities.CarDetails
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CarDetailsResourceAssembler : RepresentationModelAssemblerSupport<CarDetails, CarDetailsResource>(
    CarController::class.java, CarDetailsResource::class.java
) {

    private val noPagination = Pageable.unpaged()

    override fun toModel(entity: CarDetails): CarDetailsResource {
        return createModelWithId(entity.car.id, entity).apply {
            add(
                linkTo<CheckupController> {
                    getCheckupsForId(entity.car.id, "desc", noPagination)
                }.withRel("checkups")
            )
        }
    }

    override fun instantiateModel(entity: CarDetails): CarDetailsResource {
        return CarDetailsResource(
            entity.car.id,
            entity.car.dateAdded,
            entity.car.productionYear,
            entity.car.manufacturerModel.manufacturer,
            entity.car.manufacturerModel.model,
            entity.car.vin,
            entity.checkupNecessary
        )
    }
}

@Relation(collectionRelation = IanaLinkRelations.ITEM_VALUE)
data class CarDetailsResource(
    val id: UUID,
    val dateAdded: String,
    val productionYear: Short,
    val manufacturer: String,
    val model: String,
    val vin: String,
    val checkupNecessary: Boolean
) : RepresentationModel<CarDetailsResource>()