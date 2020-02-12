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
    fun getAllMusics(): Flux<MusicDto>?

    @GetMapping("/{id}")
    fun getMusicById(@PathVariable id: Long): Mono<MusicDto>

    @PostMapping
    fun createMusic(@RequestBody musicDto: MusicDto): Mono<MusicDto>

}
