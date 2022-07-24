package com.infinum.course.car.checkup.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.stereotype.Service

@Service
class CacheService {

    @CachePut("validCar")
    fun carValidatonCache(isValid: Boolean): Boolean {
        println("caching $isValid for inserted car...")
        return isValid
    }

    @CacheEvict("validCar")
    fun evictValidationCache() {
        println("Removing from cache...")
    }
}