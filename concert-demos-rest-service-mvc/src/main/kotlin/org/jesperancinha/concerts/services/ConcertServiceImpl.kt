package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.converters.ConcertConverter
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.repos.ConcertRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ConcertServiceImpl(
        private val concertRepository: ConcertRepository,
        private val concertConverter: ConcertConverter
) : ConcertService {

    @Transactional
    override fun getAllConcerts(): List<ConcertDto> {
        return concertRepository.findAll().map {
            concertConverter.toConcertDto(it)
        }
    }

    @Transactional
    override fun createConcert(concertDto: ConcertDto): ConcertDto {
        return concertConverter.toConcertDto(
                concertRepository.save(concertConverter.toConcert(concertDto))
        )
    }
}