package org.jesperancinha.concerts.webflux.controllers

import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.webflux.services.ListingService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/concerts/data/listings")
class ListingControllerImpl(private val listingService: ListingService) : ListingController {
    override fun getAllListings(): Flux<ListingDto>? {
        return listingService.getAllListings()
    }

    override fun getListingById(id: Long): Mono<ListingDto> {
        return listingService.getListingById(id)
    }

    override fun createListing(listingDto: ListingDto): Mono<ListingDto> {
        return listingService.createListing(listingDto)
    }
}