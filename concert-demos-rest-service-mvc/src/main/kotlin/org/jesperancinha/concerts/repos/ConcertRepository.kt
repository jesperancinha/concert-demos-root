package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Concert
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
@Transactional
interface ConcertRepository : CrudRepository<Concert, Long>