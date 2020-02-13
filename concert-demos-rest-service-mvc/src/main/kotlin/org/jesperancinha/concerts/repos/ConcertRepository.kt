package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Concert
import org.springframework.data.repository.CrudRepository

interface ConcertRepository : CrudRepository<Concert, Long>