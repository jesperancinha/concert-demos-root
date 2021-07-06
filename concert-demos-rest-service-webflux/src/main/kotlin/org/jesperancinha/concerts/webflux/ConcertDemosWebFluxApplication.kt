package org.jesperancinha.concerts.webflux

import org.jesperancinha.concerts.webflux.configuration.ConfigurationProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableConfigurationProperties(ConfigurationProperties::class)
@EnableR2dbcRepositories
open class ConcertDemosWebFluxApplication

fun main(args: Array<String>) {
    System.setProperty("reactor.netty.ioWorkerCount", "1000");
    runApplication<ConcertDemosWebFluxApplication>(*args)
}
