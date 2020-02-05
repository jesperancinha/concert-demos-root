package org.jesperancinha.concerts

import org.jesperancinha.concerts.configuration.ConfigurationProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableConfigurationProperties(ConfigurationProperties::class)
class ConcertDemosRootApplication

fun main(args: Array<String>) {
    runApplication<ConcertDemosRootApplication>(*args)
}

