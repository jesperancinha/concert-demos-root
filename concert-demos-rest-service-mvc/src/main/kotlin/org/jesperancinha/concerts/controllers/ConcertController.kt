package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.data.ConcertDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

interface ConcertController {

    @GetMapping
    fun getAllConcerts(): List<ConcertDto>

    @PostMapping
    fun createConcert(@RequestBody concertDto: ConcertDto): ConcertDto

}
