package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.converters.ListingConverter
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.repos.ListingRepository
import org.springframework.stereotype.Service

@Service
class ListingServiceImpl(
        private val listingRepository: ListingRepository,
        private val listingConverter: ListingConverter
) : ListingService {

    override fun getAllListings(): List<ListingDto>? {
        return listingRepository.findAll().map { listingConverter.toListingDto(it) }
    }

    override fun getListingById(id: Long): ListingDto {
        return listingConverter.toListingDto(listingRepository.findById(id).orElse(null))
    }


    override fun createListing(listingDto: ListingDto): ListingDto {
        val listing = listingRepository.save(listingConverter.toListing(listingDto))
        listingDto.id = listing.id
        return listingConverter.toListingDto(listingRepository.save(listingConverter.toFullListing(listingDto)))
    }
}