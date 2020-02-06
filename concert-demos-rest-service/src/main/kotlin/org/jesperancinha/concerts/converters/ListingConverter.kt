package org.jesperancinha.concerts.converters

import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.model.Listing

object ListingConverter {
    fun toListingDto(listing: Listing, artistDto: ArtistDto, musicDto: MusicDto?): ListingDto {
        return ListingDto(
                listing.id,
                artistDto,
                musicDto
        )
    }
}