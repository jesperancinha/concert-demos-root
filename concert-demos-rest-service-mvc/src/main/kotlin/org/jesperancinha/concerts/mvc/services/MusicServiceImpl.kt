package org.jesperancinha.concerts.mvc.services

import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.daos.MusicRepository
import org.jesperancinha.concerts.mvc.daos.toMusic
import org.jesperancinha.concerts.mvc.daos.toMusicDto
import org.springframework.stereotype.Service

@Service
class MusicServiceImpl(private val musicRepository: MusicRepository) : MusicService {
    override fun getAllMusics(): List<MusicDto>? {
        return musicRepository.findAll().map { it.toMusicDto() }
    }

    override fun getMusicById(id: Long): MusicDto {
        return musicRepository.findById(id).orElse(null).toMusicDto()
    }

    override fun createMusic(musicDto: MusicDto): MusicDto {
        return musicRepository.save(musicDto.toMusic()).toMusicDto()
    }
}
