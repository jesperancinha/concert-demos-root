package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Concert
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ConcertRepository : ReactiveCrudRepository<Concert, Long>
