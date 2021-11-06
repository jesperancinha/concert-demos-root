package org.jesperancinha.concerts.mvc.converters

import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.mvc.model.Listing
import org.jesperancinha.concerts.mvc.model.toArtist
import org.jesperancinha.concerts.mvc.model.toArtistDto
import org.jesperancinha.concerts.mvc.repos.MusicRepository
import org.springframework.stereotype.Component

@Component
class ListingConverter(
    private val musicRepository: MusicRepository,

    ) {
    fun toListingDto(listing: Listing): ListingDto {
        return ListingDto(
            listing.id,
            listing.artist.toArtistDto(),
            MusicConverter.toMusicDto(listing.referenceMusic),
            listing.musics.map { MusicConverter.toMusicDto(it) }.toMutableList()
        )

    }

    fun toListing(listingDto: ListingDto): Listing {
        return Listing(
            listingDto.id,
            listingDto.artistDto.toArtist(),
            MusicConverter.toMusic(listingDto.referenceMusicDto)
        )
    }

    fun toFullListing(listingDto: ListingDto): Listing {
        val listing = Listing(
            listingDto.id,
            listingDto.artistDto.toArtist(),
            MusicConverter.toMusic(listingDto.referenceMusicDto)
        )
        listing.musics = listingDto.musicDtos.map {
            val music = musicRepository.findById(it?.id ?: -1).orElse(null)
            music.listings.add(listing)
            musicRepository.save(music)
        }.toMutableSet()
        return listing
    }
}