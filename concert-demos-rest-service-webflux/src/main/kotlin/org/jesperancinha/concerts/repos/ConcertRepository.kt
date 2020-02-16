package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Concert
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ConcertRepository : ReactiveCrudRepository<Concert, Long>
