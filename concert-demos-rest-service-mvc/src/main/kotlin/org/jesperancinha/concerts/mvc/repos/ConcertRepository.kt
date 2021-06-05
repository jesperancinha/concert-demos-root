package org.jesperancinha.concerts.mvc.repos

import org.jesperancinha.concerts.mvc.model.Concert
import org.springframework.data.repository.CrudRepository

interface ConcertRepository : CrudRepository<Concert, Long>