package org.jesperancinha.concerts.mvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ConcertDemosMvcApplication {
    companion object {
        fun main(args: Array<String>) {
            runApplication<ConcertDemosMvcApplication>(*args)
        }
    }
}
