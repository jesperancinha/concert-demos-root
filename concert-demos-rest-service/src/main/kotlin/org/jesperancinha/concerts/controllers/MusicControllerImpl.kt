package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.services.MusicService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/concerts/data/music")
class MusicControllerImpl(private val musicService: MusicService) : MusicController {
    override fun getAllMusics(): Flux<MusicDto>? {
        return musicService.getAllMusics()
    }

    override fun getMusicById(id: Long): Mono<MusicDto> {
        return musicService.getMusicById(id)
    }

    override fun createMusic(musicDto: MusicDto): Mono<MusicDto> {
        return musicService.createMusic(musicDto)
    }
}