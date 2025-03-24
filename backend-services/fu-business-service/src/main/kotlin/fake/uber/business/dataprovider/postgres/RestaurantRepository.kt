package fake.uber.business.dataprovider.postgres

import com.fasterxml.jackson.databind.ObjectMapper
import fake.uber.business.dataprovider.postgres.entity.BusinessEntity
import fake.uber.business.dataprovider.postgres.statements.BusinessStatements.GET_BUSINESS_BY_ID
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
    private val objectMapper: ObjectMapper,
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

        return query.firstOrNull()?.toDomain(objectMapper)
    }
}
