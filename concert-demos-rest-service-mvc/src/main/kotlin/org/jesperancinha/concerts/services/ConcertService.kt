package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.data.ConcertDto

interface ConcertService {
    fun getAllConcerts(): List<ConcertDto>

    fun createConcert(concertDto: ConcertDto): ConcertDto
}