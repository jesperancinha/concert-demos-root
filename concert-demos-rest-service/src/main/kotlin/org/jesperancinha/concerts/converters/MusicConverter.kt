package org.jesperancinha.concerts.converters

import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.model.Music

object MusicConverter {

    fun toMusicDto(music: Music): MusicDto {
        return MusicDto(
                music.name,
                music.lyrics,
                ArtistConverter.toArtistDto(music.artist)
        )
    }
}