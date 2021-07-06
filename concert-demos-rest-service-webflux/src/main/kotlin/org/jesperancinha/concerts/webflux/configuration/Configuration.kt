package org.jesperancinha.concerts.webflux.configuration

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.r2dbc.core.DatabaseClient
import reactor.core.publisher.Mono
import java.io.BufferedReader
import java.net.URISyntaxException


private val logger = KotlinLogging.logger {}

@Configuration
class Configuration(
    @Value("\${org.jesperancinha.concerts.schema.file:/schema.sql}")
    val schema: String,
) {

    @Bean
    fun seeder(databaseClient: DatabaseClient): ApplicationRunner? {
        return ApplicationRunner {
            getSchema().flatMap { sql: String ->
                executeSql(databaseClient, sql)
            }
                .subscribe { logger.info("Schema created") }
        }
    }

    @Throws(URISyntaxException::class)
    private fun getSchema(): Mono<String> {
        val reader = BufferedReader(Configuration::class.java.getResourceAsStream(schema).reader())
        reader.use {
            val content: String = reader.readText()
            return Mono.just(content)
        }
    }

    private fun executeSql(client: DatabaseClient, sql: String): Mono<Int> {
        if (sql.isEmpty()) {
            return Mono.just(0)
        }
        return client.sql(sql).fetch().rowsUpdated()
    }
}