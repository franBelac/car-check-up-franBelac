package com.infinum.course.car.checkup


import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class CarCheckupIntegrationTest constructor(@Autowired var mockMvc: MockMvc)  {

//    @Autowired
//    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var carCheckUpRepository: InMemoryCarCheckUpRepository

    @Test
    fun testAddingCar() {
        mockMvc.get("/car-details/1")
            .andExpect {
                status { is2xxSuccessful() }
            }
        mockMvc.get("/car-details/-1")
            .andExpect {
                status { is4xxClientError() }
            }
    }

    @Test
    fun testGettingAnalytics() {
    mockMvc.get("/checkup-analytics")
        .andExpect { carCheckUpRepository.getCheckupAnalytics() }
    }

}