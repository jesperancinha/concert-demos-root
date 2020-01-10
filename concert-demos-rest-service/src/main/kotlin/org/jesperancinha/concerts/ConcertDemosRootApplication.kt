package org.jesperancinha.concerts

import org.jesperancinha.concerts.configuration.ConfigurationProperties
import org.jesperancinha.concerts.model.Artist
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ConfigurationProperties::class)
class ConcertDemosRootApplication

fun main(args: Array<String>) {
    runApplication<ConcertDemosRootApplication>(*args)
}

