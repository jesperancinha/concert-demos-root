package org.jesperancinha.concerts.webflux

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server
import org.jesperancinha.concerts.webflux.configuration.ConfigurationProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableConfigurationProperties(ConfigurationProperties::class)
@EnableR2dbcRepositories
@OpenAPIDefinition(
    info = Info(title = "OpenAPI definition"),
    servers = [Server(url = "\${concerts.server.url}", description = "Server URL")]
)
open class ConcertDemosWebFluxApplication

fun main(args: Array<String>) {
    System.setProperty("reactor.netty.ioWorkerCount", "1000");
    runApplication<ConcertDemosWebFluxApplication>(*args)
}
