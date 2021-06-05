package org.jesperancinha.concerts.webflux.converters

import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.webflux.model.Music

object MusicConverter {

    fun toMusicDto(music: Music): MusicDto {
        return MusicDto(
            music.id,
            music.name,
            music.lyrics
        )
    }

    fun toMusic(musicDto: MusicDto): Music {
        return Music(
            musicDto.id,
            musicDto.name!!,
            musicDto.lyrics!!
        )
    }
}