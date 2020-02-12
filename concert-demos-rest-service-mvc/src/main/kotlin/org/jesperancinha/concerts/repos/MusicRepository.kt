package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Music
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MusicRepository : CrudRepository<Music, Long>
