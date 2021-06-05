package org.jesperancinha.concerts.converters

import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.model.Listing

/**
 *
 * add dummy line to get coveralls to work because of know issue (trauto…
 * https://github.com/societe-generale/github-crawler/commit/035e4290b19e8df682ff1ee92f9fcbd4b12c0b63
 */
object ListingConverter {
    fun toListingDto(listing: Listing, artistDto: ArtistDto, musicDto: MusicDto): ListingDto {
        return ListingDto(
            listing.id,
            artistDto,
            musicDto
        )
    }

    fun toListing(listingDto: ListingDto): Listing {
        return Listing(
            listingDto.id,
            listingDto.artistDto?.id!!,
            listingDto.referenceMusicDto?.id!!
        )
    }
//
//
//
//
//
}
