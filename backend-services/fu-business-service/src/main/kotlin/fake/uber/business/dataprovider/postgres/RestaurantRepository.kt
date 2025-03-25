package fake.uber.business.dataprovider.postgres

import fake.uber.business.dataprovider.postgres.entity.BusinessEntity
import fake.uber.business.dataprovider.postgres.statements.BusinessStatements.GET_BUSINESS_BY_ID
import fake.uber.business.dataprovider.postgres.statements.BusinessStatements.GET_NEARBY_BUSINESS
import fake.uber.business.domain.entity.Business
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class RestaurantRepository(
    @Qualifier("writeJdbcTemplate") private val writeJdbcTemplate: NamedParameterJdbcTemplate,
    @Qualifier("readJdbcTemplate") private val readJdbcTemplate: NamedParameterJdbcTemplate,
) {
    fun findById(
        id: UUID,
        isReadOnly: Boolean = true,
    ): Business? {
        val jdbcTemplate = if (isReadOnly) readJdbcTemplate else writeJdbcTemplate
        val params = MapSqlParameterSource().addValue("id", id)

        val query =
            jdbcTemplate.query(
                GET_BUSINESS_BY_ID,
                params,
                BusinessEntity.Mapper,
            )

        return query.firstOrNull()?.toDomain()
    }

    fun getNearby(
        lat: Double,
        lng: Double,
        radius: Int,
        page: Long,
        size: Long,
        isReadOnly: Boolean = true,
    ): List<Business> {
        val jdbcTemplate = if (isReadOnly) readJdbcTemplate else writeJdbcTemplate
        val params = MapSqlParameterSource().addValue("page", page).addValue("size", size + 1)

        val query =
            jdbcTemplate.query(
                GET_NEARBY_BUSINESS,
                params,
                BusinessEntity.Mapper,
            )
        return query.map { it.toDomain() }
    }
}
