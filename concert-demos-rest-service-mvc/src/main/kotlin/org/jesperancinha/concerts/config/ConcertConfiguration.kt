package org.jesperancinha.concerts.config

import org.jesperancinha.concerts.repos.ArtistRepository
import org.jesperancinha.concerts.repos.MusicRepository
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("dev")
internal class ConcertConfiguration(
        private val artistRepository: ArtistRepository,
        private val musicRepository: MusicRepository
) {

    init {
        artistRepository.deleteAll()
        musicRepository.deleteAll()
    }

}
