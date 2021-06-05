package org.jesperancinha.concerts.webflux.repos

import org.jesperancinha.concerts.webflux.model.Concert
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ConcertRepository : ReactiveCrudRepository<Concert, Long>
