package org.jesperancinha.concerts.mvc.controllers

import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.mvc.services.ConcertService
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/concerts/data/concerts")
class ConcertControllerImpl(private val concertService: ConcertService) : ConcertController {
    override fun getAllConcerts(): List<ConcertDto> {
        return concertService.getAllConcerts()
    }

    @Transactional
    override fun createConcert(concertDto: ConcertDto): ConcertDto {
        return concertService.createConcert(concertDto)
    }
}