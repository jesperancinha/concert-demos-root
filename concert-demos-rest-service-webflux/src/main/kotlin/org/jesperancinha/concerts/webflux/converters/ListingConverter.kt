package org.jesperancinha.concerts.webflux.converters

import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.webflux.model.Listing

object ListingConverter {
    fun toListingDto(listing: Listing, artistDto: ArtistDto, musicDto: MusicDto): ListingDto {
        return ListingDto(
            listing.id,
            artistDto,
            musicDto,
            mutableListOf()
        )
    }

    fun toListing(listingDto: ListingDto): Listing {
        return Listing(
            listingDto.id,
            listingDto.artistDto.id,
            listingDto.referenceMusicDto.id
        )
    }
}
