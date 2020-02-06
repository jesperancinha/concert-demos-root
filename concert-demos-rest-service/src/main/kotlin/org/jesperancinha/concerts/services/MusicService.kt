package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.model.Music
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface MusicService {
    fun getAllMusics(): Flux<Music?>?

    fun createMusic(music: Music): Mono<Music>
}