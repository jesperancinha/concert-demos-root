package org.jesperancinha.concerts.mvc.services

import org.jesperancinha.concerts.mvc.converters.MusicConverter
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.repos.MusicRepository
import org.springframework.stereotype.Service

@Service
class MusicServiceImpl(private val musicRepository: MusicRepository) : MusicService {
    override fun getAllMusics(): List<MusicDto>? {
        return musicRepository.findAll().map { MusicConverter.toMusicDto(it) }
    }

    override fun getMusicById(id: Long): MusicDto {
        return MusicConverter.toMusicDto(musicRepository.findById(id).orElse(null))
    }

    override fun createMusic(music: MusicDto): MusicDto {
        return MusicConverter.toMusicDto(musicRepository.save(MusicConverter.toMusic(music)))
    }
}
