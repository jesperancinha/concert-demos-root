package org.jesperancinha.concerts.converters

import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.model.Concert
import org.jesperancinha.concerts.repos.ListingRepository
import org.springframework.stereotype.Component

@Component
class ConcertConverter(
        private val listingConverter: ListingConverter,
        private val listingRepository: ListingRepository
) {
    fun toConcertDto(concert: Concert): ConcertDto {
        return ConcertDto(
                concert.id,
                concert.name!!,
                concert.location!!,
                concert.date!!,
                concert.listings?.map { listingConverter.toListingDto(it) }?.toMutableList()
        )
    }

    fun toConcert(concertDto: ConcertDto): Concert {
        val concert = Concert(concertDto.id, concertDto.name, concertDto.location, concertDto.date)
        concert.listings = concertDto.listingDtos?.map {
            val listing = listingRepository.findById(it.id!!).orElse(null)
            listing.concerts.add(concert)
            listing
        }?.toMutableSet()
        return concert
    }
}