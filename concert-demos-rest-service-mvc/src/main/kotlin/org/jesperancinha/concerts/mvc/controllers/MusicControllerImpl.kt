package org.jesperancinha.concerts.mvc.controllers

import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.services.MusicService
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/concerts/data/musics")
class MusicControllerImpl(private val musicService: MusicService) : MusicController {
    override fun getAllMusics(): List<MusicDto>? {
        return musicService.getAllMusics()
    }

    override fun getMusicById(id: Long): MusicDto {
        return musicService.getMusicById(id)
    }

    @Transactional
    override fun createMusic(musicDto: MusicDto): MusicDto {
        return musicService.createMusic(musicDto)
    }
}