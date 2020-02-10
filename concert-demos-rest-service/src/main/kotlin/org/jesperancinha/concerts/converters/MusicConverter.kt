package org.jesperancinha.concerts.converters

import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.model.Music

object MusicConverter {

    fun toMusicDto(Music: Music): MusicDto {
        return MusicDto(
                Music.name,
                Music.lyrics
        )
    }

    fun toMusic(musicDto: MusicDto): Music {
        return Music(
                null,
                musicDto.name,
                musicDto.lyrics
        )
    }
}