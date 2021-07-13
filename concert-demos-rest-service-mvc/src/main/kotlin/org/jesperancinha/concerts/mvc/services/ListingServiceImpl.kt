package org.jesperancinha.concerts.mvc.services

import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.mvc.converters.ListingConverter
import org.jesperancinha.concerts.mvc.repos.ListingRepository
import org.springframework.stereotype.Service

@Service
class ListingServiceImpl(
    private val listingRepository: ListingRepository,
    private val listingConverter: ListingConverter,
) : ListingService {

    override fun getAllListings(): List<ListingDto>? {
        return listingRepository.findAll().map { listingConverter.toListingDto(it) }
    }

    override fun getListingById(id: Long): ListingDto {
        return listingConverter.toListingDto(listingRepository.findById(id).orElse(null))
    }


    override fun createListing(listingDto: ListingDto): ListingDto {
        val listing = listingRepository.save(listingConverter.toListing(listingDto))
        val newListingDto = ListingDto(listing.id, listingDto.artistDto, listingDto.referenceMusicDto, listingDto.musicDtos)
        return listingConverter
            .toListingDto(listingRepository.save(listingConverter.toFullListing(newListingDto)))
    }
}