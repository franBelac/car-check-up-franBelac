package com.infinum.course.car.checkup.controller

import com.infinum.course.car.checkup.controller.dto.CheckupAnalyticsResource
import com.infinum.course.car.checkup.controller.dto.CheckupAnalyticsResourceAssembler
import com.infinum.course.car.checkup.entities.checkupEntities.CarCheckUp
import com.infinum.course.car.checkup.entities.checkupEntities.CheckupClientSide
import com.infinum.course.car.checkup.service.CarCheckUpService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*


@Controller
class CheckupController(
    private val carCheckUpService: CarCheckUpService,
    private val checkupAnalyticsResourceAssembler: CheckupAnalyticsResourceAssembler
) {

    @PostMapping("/checkup")
    @ResponseBody
    fun addCheckup(@RequestBody checkupClientSide: CheckupClientSide): ResponseEntity<UUID> = ResponseEntity(
        carCheckUpService.addCheckup(checkupClientSide), HttpStatus.CREATED
    )

    @GetMapping("/checkup-analytics")
    @ResponseBody
    fun getCheckupAnalytics(): ResponseEntity<CheckupAnalyticsResource> = ResponseEntity.ok(
        checkupAnalyticsResourceAssembler.toModel(
            carCheckUpService.getCheckupAnalytics()
        )
    )

    @GetMapping("/paged/checkups")
    fun getCheckupsForId(
        @RequestParam(defaultValue = "") checkedCarId: UUID,
        @RequestParam(defaultValue = "desc") sortBy: String,
        pageable: Pageable
    ): ResponseEntity<Page<CarCheckUp>> =
        ResponseEntity.ok(
            carCheckUpService.getCheckupsById(checkedCarId, pageable, sortBy)
        )

}