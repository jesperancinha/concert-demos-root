package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.data.ListingDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ListingController {

    @GetMapping
    fun getAllListings(): List<ListingDto>?

    @GetMapping("/{id}")
    fun getListingById(@PathVariable id: Long): ListingDto

    @PostMapping
    fun createListing(@RequestBody listingDto: ListingDto): ListingDto

}
