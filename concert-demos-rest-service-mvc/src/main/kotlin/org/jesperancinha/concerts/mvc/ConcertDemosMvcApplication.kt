package org.jesperancinha.concerts.mvc

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactory")
@OpenAPIDefinition(
    info = Info(title = "OpenAPI definition"),
    servers = [Server(url = "\${concerts.server.url}", description = "Server URL")]
)
class ConcertDemosMvcApplication

fun main(args: Array<String>) {
    runApplication<ConcertDemosMvcApplication>(*args)
}
