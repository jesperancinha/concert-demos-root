package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.converters.ConcertConverter
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.repos.ConcertRepository
import org.springframework.stereotype.Service

@Service
class ConcertServiceImpl(
        private val concertRepository: ConcertRepository
) : ConcertService {
    override fun getAllConcerts(): List<ConcertDto> {
        return concertRepository.findAll().map { ConcertConverter.toConcertDto(it) }
    }

    override fun createConcert(concertDto: ConcertDto): ConcertDto {
        return ConcertConverter.toConcertDto(concertRepository.save(ConcertConverter.toConcert(concertDto)))
    }
}