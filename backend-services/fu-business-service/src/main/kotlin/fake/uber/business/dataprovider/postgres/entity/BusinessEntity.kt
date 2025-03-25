package fake.uber.business.dataprovider.postgres.entity

import fake.uber.business.domain.entity.Business
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.util.UUID

data class BusinessEntity(
    val id: UUID,
    val name: String,
    val lat: Double,
    val lng: Double,
) {
    fun toDomain(): Business =
        Business(
            id = id,
            name = name,
            lat = lat,
            lng = lng,
        )

    companion object Mapper : RowMapper<BusinessEntity> {
        override fun mapRow(
            rs: ResultSet,
            rowNum: Int,
        ): BusinessEntity {
            val id = rs.getObject("id", UUID::class.java)
            val name = rs.getString("name")

            return BusinessEntity(
                id = id,
                name = name,
                lat = rs.getDouble("lat"),
                lng = rs.getDouble("lng"),
            )
        }
    }
}
