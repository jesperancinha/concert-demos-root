package org.jesperancinha.concerts.mvc.services

import org.jesperancinha.concerts.data.ConcertDto

interface ConcertService {
    fun getAllConcerts(): List<ConcertDto>

    fun createConcert(concertDto: ConcertDto): ConcertDto
}