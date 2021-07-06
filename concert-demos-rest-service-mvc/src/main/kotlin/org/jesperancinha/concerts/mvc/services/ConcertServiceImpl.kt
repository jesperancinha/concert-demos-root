package org.jesperancinha.concerts.mvc.services

import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.mvc.converters.ConcertConverter
import org.jesperancinha.concerts.mvc.repos.ConcertRepository
import org.springframework.stereotype.Service

@Service
class ConcertServiceImpl(
    private val concertRepository: ConcertRepository,
    private val concertConverter: ConcertConverter,
) : ConcertService {


    override fun getAllConcerts(): List<ConcertDto> {
        return concertRepository.findAll().map {
            concertConverter.toConcertDto(it)
        }
    }


    override fun createConcert(concertDto: ConcertDto): ConcertDto {
        return concertConverter.toConcertDto(
            concertRepository.save(concertConverter.toConcert(concertDto))
        )
    }
}