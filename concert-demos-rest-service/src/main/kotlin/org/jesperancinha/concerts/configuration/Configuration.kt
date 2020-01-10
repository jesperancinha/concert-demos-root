package org.jesperancinha.concerts.configuration

import io.netty.util.internal.StringUtil
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD
import io.r2dbc.spi.ConnectionFactoryOptions.USER
import mu.KotlinLogging
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.DatabaseClient
import reactor.core.publisher.Mono
import java.io.BufferedReader
import java.lang.ClassLoader.getSystemResourceAsStream
import java.net.URISyntaxException

private val logger = KotlinLogging.logger {}

@Configuration
class Configuration {

    @Bean
    fun seeder(client: DatabaseClient): ApplicationRunner? {
        return ApplicationRunner { args: ApplicationArguments? ->
            getSchema().flatMap { sql: String -> executeSql(client, sql) }
                    .subscribe { count: Any? -> logger.info("Schema created") }
        }
    }

    @Throws(URISyntaxException::class)
    private fun getSchema(): Mono<String> {
        val reader = BufferedReader(getSystemResourceAsStream("schema.sql").reader())
        val content: String
        try {
            content = reader.readText()
        } finally {
            reader.close()
        }
        return Mono.just(content);
    }

    private fun executeSql(client: DatabaseClient, sql: String): Mono<Int> {
        return client.execute(sql).fetch().rowsUpdated()
    }
}