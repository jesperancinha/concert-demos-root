package org.jesperancinha.concerts.webflux

import org.jesperancinha.concerts.webflux.configuration.ConfigurationProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ConfigurationProperties::class)
class ConcertDemosWebFluxApplication {
    companion object {
        fun main(args: Array<String>) {
            System.setProperty("reactor.netty.ioWorkerCount", "1000");
            runApplication<ConcertDemosWebFluxApplication>(*args)
        }
    }
}

