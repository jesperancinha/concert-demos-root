package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.model.Music
import org.jesperancinha.concerts.repos.MusicRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class MusicServiceImpl(private val musicRepository: MusicRepository) : MusicService {
    override fun getAllMusics(): Flux<Music?>? {
        return musicRepository.findAll()
    }

    override fun createMusic(music: Music): Mono<Music> {
        return musicRepository.save(music)
    }
}