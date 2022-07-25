package com.infinum.course.car.checkup


import com.fasterxml.jackson.databind.ObjectMapper
import com.infinum.course.car.checkup.entities.checkupEntities.CarCheckUp
import com.infinum.course.car.checkup.entities.carEntities.CarClientSide
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.util.*


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
                    performedAt = "2015-07-23T02:04:38.344699600",
                    workerName = "Fabio",
                    price = 410,
                    checkedCarId = UUID.randomUUID()
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
                is2xxSuccessful()
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

    @Test
    fun testPaginationCars() {
        mockMvc.get("/paged/cars").andExpect {
            status {
                is2xxSuccessful()
            }
        }
    }

    @Test
    fun testPaginationCheckups() {
        mockMvc.get("/paged/checkups?checkedCarId=2d3212f8-58e9-47f4-bbe6-a8441496eaf6&page=0&size=2")
            .andExpect { status {
                is2xxSuccessful()
            } }
    }

}