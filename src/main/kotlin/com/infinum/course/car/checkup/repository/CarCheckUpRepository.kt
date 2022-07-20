package com.infinum.course.car.checkup.repository

import com.infinum.course.car.checkup.entities.*
import com.infinum.course.car.checkup.entities.helpers.CarAnalyticsModel
import org.springframework.dao.IncorrectResultSizeDataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.YEARS

@Repository
class CarCheckUpRepository(
   private val jdbcTemplate: NamedParameterJdbcTemplate
) {

    fun addCar(car: Car): Long {
        val keyHolder = GeneratedKeyHolder()
        val sql = "INSERT INTO cars (dateAdded,productionYear,manufacturer,model,vin) values  (:dateAdded,:productionYear,:manufacturer,:model,:vin)"

        jdbcTemplate.update(
            sql,
         MapSqlParameterSource(   mapOf(
                "dateAdded" to car.dateAdded,
                "productionYear" to car.productionYear,
                "manufacturer" to car.manufacturer,
                "model" to car.model,
                "vin" to car.vin
            )
         ),
            keyHolder,
            arrayOf("id")
        )
        return keyHolder.key?.toLong() ?: -1L
    }


    fun fetchCarDetails(id: Long) : CarDetails {

        val car : CarClientSide

        try {
             car = jdbcTemplate.queryForObject(
                "SELECT * FROM cars WHERE id = :id",
                mapOf("id" to id)
            ) { resultSet, _ ->
                CarClientSide(
                    productionYear = resultSet.getShort("productionYear"),
                    manufacturer = resultSet.getString("manufacturer"),
                    model = resultSet.getString("model"),
                    vin = resultSet.getString("vin"),

                    )
            } ?: throw CarNotFoundException(id)

        } catch (e : IncorrectResultSizeDataAccessException ) {
            throw CarNotFoundException(id)
        }

        val rowMapper = RowMapper { resultSet: ResultSet, _ ->
                CarCheckUp(
                    LocalDateTime.parse(resultSet.getString("performedat")),
                    resultSet.getString("workername"),
                    resultSet.getLong("price"),
                    resultSet.getLong("carid")
                )
        }

        val checkUps = jdbcTemplate.query(
            "SELECT * FROM carcheckups where carid = :id",
            mapOf("id" to id),
            rowMapper
        ).sortedByDescending { it.performedAt }

        val noCheckupNeeded = if (checkUps.isEmpty()) false else (YEARS.between(checkUps[0].performedAt,LocalDateTime.now()) == 0L)
        val checkupNecessary = if(noCheckupNeeded)  "This car doesn't need a checkup" else "This car needs a checkup"

        return CarDetails(car,checkUps,checkupNecessary)
    }


    fun addCheckup(carCheckUp: CarCheckUp): Long {

        jdbcTemplate.queryForList(
            "SELECT * FROM cars WHERE id = :id",
            mapOf("id" to carCheckUp.carId)
        ).ifEmpty { throw CarCHeckUpInvalidException() }

        val keyHolder = GeneratedKeyHolder()

        jdbcTemplate.update(
            "INSERT INTO carcheckups(performedAt,workerName,price,carId) VALUES (:performedAt,:workerName,:price,:carId)",
            MapSqlParameterSource( mapOf(
                "performedAt" to carCheckUp.performedAt.toString(),
                "workerName" to carCheckUp.workerName,
                "price" to carCheckUp.price,
                "carId" to carCheckUp.carId
            )
            ),
            keyHolder,
            arrayOf("id")
        )
        return keyHolder.key?.toLong() ?: -1L
    }

    fun getCheckupAnalytics(): Map<String,Int> {

        val rowMapper = RowMapper {
            resultSet : ResultSet,_ ->
                CarAnalyticsModel(
                    manufacturer = resultSet.getString("manufacturer"),
                    checkupCount = resultSet.getInt("checkupcount")
                )
        }

        val listOfAnalytics = jdbcTemplate.query(
            "select cars.manufacturer, COALESCE(COUNT(carcheckups.carid),0) as checkupcount" +
                    "  FROM cars left join carcheckups on cars.id = carcheckups.carId" +
                    " group by cars.manufacturer ",
            rowMapper
        )

        return listOfAnalytics.associateBy({it.manufacturer}, {it.checkupCount})
    }
}
