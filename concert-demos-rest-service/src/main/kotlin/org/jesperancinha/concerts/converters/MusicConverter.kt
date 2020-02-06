package org.jesperancinha.concerts.converters

import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.model.Music

object MusicConverter {

    fun toMusicDto(music: Music): MusicDto {
        return MusicDto(
                music.name,
                music.lyrics
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