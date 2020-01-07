package org.jesperancinha.concerts.configuration

import io.netty.util.internal.StringUtil
import io.r2dbc.spi.Connection
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD
import io.r2dbc.spi.ConnectionFactoryOptions.USER
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux

@Configuration
 class Configuration {

    @Bean
    fun connectionFactory(properties: ConfigurationProperties): ConnectionFactory? {
        val baseOptions = ConnectionFactoryOptions.parse(properties.getUrl()!!)
        var ob: ConnectionFactoryOptions.Builder = ConnectionFactoryOptions.builder().from(baseOptions)
        if (!StringUtil.isNullOrEmpty(properties.getUser())) {
            ob = ob.option(USER, properties.getUser()!!)
        }
        if (!StringUtil.isNullOrEmpty(properties.getPassword())) {
            ob = ob.option(PASSWORD, properties.getPassword()!!)
        }
        return ConnectionFactories.get(ob.build())
    }

    @Bean
    fun initDatabase(connectionFactory: ConnectionFactory): CommandLineRunner? {
        return CommandLineRunner { args: Array<String?>? ->
            Flux.from(connectionFactory.create())
                    .flatMap { c: Connection? ->
                        Flux.from(c!!.createBatch()
                                .add("drop table if exists Account")
                                .add("create table Account(" +
                                        "id IDENTITY(1,1)," +
                                        "iban varchar(80) not null," +
                                        "balance DECIMAL(18,2) not null)")
                                .add("insert into Account(iban,balance)" +
                                        "values('BR430120980198201982',100.00)")
                                .add("insert into Account(iban,balance)" +
                                        "values('BR430120998729871000',250.00)")
                                .execute())
                                .doFinally { c.close() }
                    }
                    .log()
                    .blockLast()
        }
    }
}