package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.services.ListingService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/concerts/data/listings")
class ListingControllerImpl(private val listingService: ListingService) : ListingController {
    override fun getAllListings(): List<ListingDto>? {
        return listingService.getAllListings()
    }

    override fun getListingById(id: Long): ListingDto {
        return listingService.getListingById(id)
    }

    override fun createListing(listingDto: ListingDto): ListingDto {
        return listingService.createListing(listingDto)
    }
}