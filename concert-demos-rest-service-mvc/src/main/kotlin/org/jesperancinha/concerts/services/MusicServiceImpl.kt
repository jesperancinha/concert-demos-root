package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.converters.MusicConverter
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.repos.MusicRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class MusicServiceImpl(private val musicRepository: MusicRepository) : MusicService {
    override fun getAllMusics(): Flux<MusicDto>? {
        return musicRepository.findAll().map { MusicConverter.toMusicDto(it) }
    }

    override fun getMusicById(id: Long): Mono<MusicDto> {
        return musicRepository.findById(id).map { MusicConverter.toMusicDto(it) }
    }

    override fun createMusic(music: MusicDto): Mono<MusicDto> {
        return musicRepository.save(MusicConverter.toMusic(music))
                .map { MusicConverter.toMusicDto(it) }
    }
}
