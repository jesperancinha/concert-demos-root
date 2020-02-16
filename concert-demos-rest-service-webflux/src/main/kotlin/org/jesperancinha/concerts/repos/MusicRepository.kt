package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Music
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface MusicRepository : ReactiveCrudRepository<Music, Long>
