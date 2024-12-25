package org.jesperancinha.concerts.mvc.controllers

import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.mvc.services.ListingService
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/concerts/data/listings")
class ListingControllerImpl(private val listingService: ListingService) : ListingController {
    override fun getAllListings(): List<ListingDto>? {
        return listingService.getAllListings()
    }

    override fun getListingById(id: Long): ListingDto {
        return listingService.getListingById(id)
    }

    @Transactional
    override fun createListing(listingDto: ListingDto): ListingDto {
        return listingService.createListing(listingDto)
    }
}