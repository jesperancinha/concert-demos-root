package org.jesperancinha.concerts.mvc.controllers

import jakarta.validation.constraints.NotNull
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.mvc.services.ArtistService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/concerts/data/artists")
class ArtistController(private val artistService: ArtistService) {

    @GetMapping
    fun getAllArtists(): List<ArtistDto>? = artistService.getAllArtists()

    @PostMapping
    fun createArtist(@RequestBody @NotNull artistDto: ArtistDto) = artistService.createArtist(artistDto)
}
