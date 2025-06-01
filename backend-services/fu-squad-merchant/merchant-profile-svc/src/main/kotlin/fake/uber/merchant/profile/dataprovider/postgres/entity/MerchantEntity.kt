package fake.uber.merchant.profile.dataprovider.postgres.entity

import fake.uber.merchant.profile.domain.entity.Merchant
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.util.UUID

data class MerchantEntity(
    val id: UUID,
    val name: String,
    val lat: Double,
    val lng: Double,
) {
    fun toDomain(): Merchant =
        Merchant(
            id = id,
            name = name,
            lat = lat,
            lng = lng,
        )

    companion object Mapper : RowMapper<MerchantEntity> {
        override fun mapRow(
            rs: ResultSet,
            rowNum: Int,
        ): MerchantEntity {
            val id = rs.getObject("id", UUID::class.java)
            val name = rs.getString("name")

            return MerchantEntity(
                id = id,
                name = name,
                lat = rs.getDouble("lat"),
                lng = rs.getDouble("lng"),
            )
        }
    }
}
