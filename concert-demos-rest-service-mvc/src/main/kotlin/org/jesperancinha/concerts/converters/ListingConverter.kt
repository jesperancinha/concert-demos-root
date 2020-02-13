package org.jesperancinha.concerts.converters

import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.model.Listing
import org.jesperancinha.concerts.repos.MusicRepository
import org.springframework.stereotype.Component

@Component
class ListingConverter(
        private val musicRepository: MusicRepository

) {
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
                MusicConverter.toMusic(listingDto.referenceMusicDto!!))
    }

    fun toFullListing(listingDto: ListingDto): Listing {
        val listing = Listing(
                listingDto.id!!,
                ArtistConverter.toArtist(listingDto.artistDto!!),
                MusicConverter.toMusic(listingDto.referenceMusicDto!!))
        listing.musics = listingDto.musicDtos?.map {
            val music = musicRepository.findById(it.id!!).orElse(null)
            music.listing = listing
            musicRepository.save(music)
        }?.toMutableSet()!!
        return listing
    }
}