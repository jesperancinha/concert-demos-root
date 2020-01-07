package org.jesperancinha.concerts.configuration

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {

    @Bean
    fun connectionFactory(properties: ConfigurationProperties): ConnectionFactory? {
        val baseOptions = ConnectionFactoryOptions.parse(properties.getUrl()!!)
        var ob: ConnectionFactoryOptions.Builder = ConnectionFactoryOptions.builder().from(baseOptions)
        return ConnectionFactories.get(ob.build())
    }
}