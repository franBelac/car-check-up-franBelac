package com.infinum.course.car.checkup

fun main() {
    // Entry point
    println(CarCheckUpSystem.isCheckupNecessary("1"))
    println(CarCheckUpSystem.isCheckupNecessary("4"))
    println(CarCheckUpSystem.countCheckUps("Ford"))
    CarCheckUpSystem.addCheckUp("4")
    println(CarCheckUpSystem.countCheckUps("Ford"))
    CarCheckUpSystem.getCheckUps("4").forEach { println(it) }
    CarCheckUpSystem.addCheckUp("5465")
}