package org.jesperancinha.concerts.webflux.repos

import org.jesperancinha.concerts.webflux.model.Concert
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ConcertRepository : ReactiveCrudRepository<Concert, Long>
