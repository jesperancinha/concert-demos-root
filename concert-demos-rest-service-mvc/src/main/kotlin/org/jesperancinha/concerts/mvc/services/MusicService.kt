package org.jesperancinha.concerts.mvc.services

import org.jesperancinha.concerts.data.MusicDto

interface MusicService {
    fun getAllMusics(): List<MusicDto>?

    fun createMusic(music: MusicDto): MusicDto

    fun getMusicById(id: Long): MusicDto
}