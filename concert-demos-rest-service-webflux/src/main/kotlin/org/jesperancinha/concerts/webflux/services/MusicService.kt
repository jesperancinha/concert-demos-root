package org.jesperancinha.concerts.webflux.services

import org.jesperancinha.concerts.data.MusicDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface MusicService {
    fun getAllMusics(): Flux<MusicDto>?

    fun createMusic(music: MusicDto): Mono<MusicDto>

    fun getMusicById(id: Long): Mono<MusicDto>
}