package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.converters.ListingConverter
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.repos.ListingRepository
import org.springframework.stereotype.Service

@Service
class ListingServiceImpl(private val listingRepository: ListingRepository) : ListingService {

    override fun getAllListings(): List<ListingDto>? {
        return listingRepository.findAll().map { ListingConverter.toListingDto(it) }
    }

    override fun getListingById(id: Long): ListingDto {
        return ListingConverter.toListingDto(listingRepository.findById(id).orElse(null))
    }

    override fun createListing(listingDto: ListingDto): ListingDto {
        return ListingConverter.toListingDto(listingRepository.save(ListingConverter.toListing(listingDto)))
    }
}