package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.services.ConcertService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/concerts/data/concerts")
class ConcertControllerImpl(private val concertService: ConcertService) : ConcertController {
    override fun getAllConcerts(): List<ConcertDto> {
        return concertService.getAllConcerts()
    }

    override fun createConcert(concertDto: ConcertDto): ConcertDto {
        return concertService.createConcert(concertDto)
    }
}