package org.jesperancinha.concerts.mvc.services

import org.jesperancinha.concerts.data.ListingDto

interface ListingService {
    fun getAllListings(): List<ListingDto>?

    fun createListing(listingDto: ListingDto): ListingDto

    fun getListingById(id: Long): ListingDto
}