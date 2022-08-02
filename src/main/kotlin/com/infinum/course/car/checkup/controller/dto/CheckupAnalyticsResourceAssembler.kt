package com.infinum.course.car.checkup.controller.dto

import com.infinum.course.car.checkup.controller.CheckupController
import com.infinum.course.car.checkup.entities.checkupEntities.CheckupAnalytics
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CheckupAnalyticsResourceAssembler :
    RepresentationModelAssemblerSupport<CheckupAnalytics, CheckupAnalyticsResource>(
        CheckupController::class.java, CheckupAnalyticsResource::class.java
    ) {
    override fun toModel(entity: CheckupAnalytics): CheckupAnalyticsResource {
        return createModelWithId(UUID.randomUUID(), entity)
    }

    override fun instantiateModel(entity: CheckupAnalytics): CheckupAnalyticsResource {
        return CheckupAnalyticsResource(
            entity.mapOfAnalytics
        )
    }

}

@Relation(collectionRelation = IanaLinkRelations.ITEM_VALUE)
data class CheckupAnalyticsResource(
    val mapOfAnalytics: Map<String, Long>
) : RepresentationModel<CheckupAnalyticsResource>()