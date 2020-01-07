package org.jesperancinha.concerts.repos

import io.r2dbc.spi.ConnectionFactory
import org.springframework.stereotype.Repository

@Repository
class ArtistRepository(private var connectionFactory: ConnectionFactory) {

}

