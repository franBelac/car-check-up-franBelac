package com.infinum.course.car.checkup.com.infinum.course.car.checkup


import com.fasterxml.jackson.databind.ObjectMapper
import com.infinum.course.car.checkup.entities.CarCheckUp
import com.infinum.course.car.checkup.entities.CarClientSide
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.time.LocalDateTime


@SpringBootTest
@AutoConfigureMockMvc
class CarCheckupIntegrationTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
    )  {

    @Test
    fun testAddingCars() {
        mockMvc.post("/add-car") {
            content = objectMapper.writeValueAsString(
                CarClientSide(
                    productionYear = 2000,
                    manufacturer = "Pagani",
                    model = "Zonda",
                    vin = "UAIHGF894"
                )
            )
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status {
                isOk()
            }
        }
    }

    @Test
    fun testAddingCheckup() {
        mockMvc.post("/add-checkup") {
            content = objectMapper.writeValueAsString(
                CarCheckUp(
                    performedAt = LocalDateTime.now(),
                    workerName = "Fabio",
                    price = 410,
                    carId = 1
                )
            )
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status {
                is4xxClientError()
            }
        }
    }

    @Test
    fun testAnalytics() {
        mockMvc.get("/checkup-analytics").andExpect {
            status {

            }
        }
    }

    @Test
    fun testCarDetails() {
        mockMvc.get("/car-details/-1").andExpect {
            status {
                is4xxClientError()
            }
        }
    }

}