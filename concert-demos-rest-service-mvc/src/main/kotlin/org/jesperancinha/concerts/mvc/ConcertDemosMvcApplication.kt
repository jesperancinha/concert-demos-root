package org.jesperancinha.concerts.mvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactory")
class ConcertDemosMvcApplication

fun main(args: Array<String>) {
    runApplication<ConcertDemosMvcApplication>(*args)
}
