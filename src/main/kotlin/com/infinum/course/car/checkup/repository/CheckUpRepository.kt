package com.infinum.course.car.checkup.repository

import com.infinum.course.car.checkup.entities.checkupEntities.CarCheckUp
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface CheckUpRepository : JpaRepository<CarCheckUp, UUID> {

    fun save(carCheckUp: CarCheckUp): CarCheckUp

    @Query(
        value =
        "select cars.manufacturer, COUNT(carcheckups.checked_car_id) as checkup_count" +
                "  FROM cars left join carcheckups on cars.id = carcheckups.checked_car_id" +
                " group by cars.manufacturer ",
        nativeQuery = true
    )
    fun getCheckupAnalytics(): List<String>


    fun findAllByCheckedCarIdOrderByPerformedAtDesc(checkedCarId: UUID, pageable: Pageable): Page<CarCheckUp>

    fun findAllByCheckedCarIdOrderByPerformedAtAsc(checkedCarId: UUID, pageable: Pageable): Page<CarCheckUp>

    override fun deleteAll()
}