package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.data.ArtistDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

interface ArtistController {

    @GetMapping
    fun getAllArtists(): List<ArtistDto>?

    @PostMapping
    fun createArtist(@RequestBody artistDto: ArtistDto): ArtistDto

}
