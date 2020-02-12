package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Concert
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ConcertRepository : CrudRepository<Concert, Long>
