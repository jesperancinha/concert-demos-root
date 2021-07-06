package org.jesperancinha.concerts.webflux.repos

import org.jesperancinha.concerts.webflux.model.Music
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

interface MusicRepository : ReactiveCrudRepository<Music, Long>
