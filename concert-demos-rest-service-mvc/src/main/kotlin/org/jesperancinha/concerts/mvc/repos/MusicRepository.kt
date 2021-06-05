package org.jesperancinha.concerts.mvc.repos

import org.jesperancinha.concerts.mvc.model.Music
import org.springframework.data.repository.CrudRepository

interface MusicRepository : CrudRepository<Music, Long>
