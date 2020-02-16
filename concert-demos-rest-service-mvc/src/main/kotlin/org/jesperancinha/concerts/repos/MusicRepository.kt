package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Music
import org.springframework.data.repository.CrudRepository

interface MusicRepository : CrudRepository<Music, Long>
