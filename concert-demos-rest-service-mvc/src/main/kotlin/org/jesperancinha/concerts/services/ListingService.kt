package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.data.ListingDto

interface ListingService {
    fun getAllListings(): List<ListingDto>?

    fun createListing(listingDto: ListingDto): ListingDto

    fun getListingById(id: Long): ListingDto
}