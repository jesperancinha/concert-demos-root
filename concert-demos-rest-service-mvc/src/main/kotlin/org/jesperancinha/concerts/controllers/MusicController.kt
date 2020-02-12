package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.data.MusicDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface MusicController {

    @GetMapping
    fun getAllMusics(): List<MusicDto>?

    @GetMapping("/{id}")
    fun getMusicById(@PathVariable id: Long): MusicDto

    @PostMapping
    fun createMusic(@RequestBody musicDto: MusicDto): MusicDto

}
