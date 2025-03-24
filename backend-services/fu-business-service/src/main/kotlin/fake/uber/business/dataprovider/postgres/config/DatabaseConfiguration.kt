package fake.uber.business.dataprovider.postgres.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import javax.sql.DataSource

@Configuration
class DatabaseConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.write", ignoreUnknownFields = true)
    fun writeDataSource(): HikariDataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.read", ignoreUnknownFields = true)
    fun readDataSource(): HikariDataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()

    @Bean
    fun writeTransactionManager(
        @Qualifier("writeDataSource") writeDataSource: DataSource,
    ): PlatformTransactionManager = DataSourceTransactionManager(writeDataSource)

    @Bean
    fun writeTransactionTemplate(
        @Qualifier("writeTransactionManager") writeTransactionManager: PlatformTransactionManager,
    ) = TransactionTemplate(writeTransactionManager)

    @Bean
    fun writeJdbcTemplate(
        @Qualifier("writeDataSource") writeDataSource: DataSource,
    ) = NamedParameterJdbcTemplate(writeDataSource)

    @Bean
    fun readJdbcTemplate(
        @Qualifier("readDataSource") readDataSource: DataSource,
    ) = NamedParameterJdbcTemplate(readDataSource)
}
