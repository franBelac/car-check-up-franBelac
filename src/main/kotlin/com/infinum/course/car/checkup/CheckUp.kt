package com.infinum.course.car.checkup

import java.time.LocalDateTime

data class CheckUp (
    var performedAt : LocalDateTime,
    var car: Car
    )
