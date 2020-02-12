package org.jesperancinha.concerts.converters

import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.model.Listing
import org.springframework.stereotype.Component

@Component
class ListingConverter {
    fun toListingDto(listing: Listing): ListingDto {
        return ListingDto(
                listing.id,
                ArtistConverter.toArtistDto(listing.artist!!),
                MusicConverter.toMusicDto(listing.referenceMusic!!),
                listing.musics?.map { MusicConverter.toMusicDto(it) }?.toMutableList()
        )

    }

    fun toListing(listingDto: ListingDto): Listing {
        return Listing(
                listingDto.id!!,
                ArtistConverter.toArtist(listingDto.artistDto!!),
                MusicConverter.toMusic(listingDto.referenceMusicDto!!),
                listingDto.musicDtos?.map { MusicConverter.toMusic(it) }?.toMutableSet()!!
        )
    }
}