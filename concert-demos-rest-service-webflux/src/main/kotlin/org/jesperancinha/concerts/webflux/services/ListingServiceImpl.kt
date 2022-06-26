package org.jesperancinha.concerts.webflux.services

import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.webflux.converters.ArtistConverter
import org.jesperancinha.concerts.webflux.converters.ListingConverter
import org.jesperancinha.concerts.webflux.converters.MusicConverter
import org.jesperancinha.concerts.webflux.model.Listing
import org.jesperancinha.concerts.webflux.model.ListingMusic
import org.jesperancinha.concerts.webflux.repos.ArtistRepository
import org.jesperancinha.concerts.webflux.repos.ListingMusicRepository
import org.jesperancinha.concerts.webflux.repos.ListingRepository
import org.jesperancinha.concerts.webflux.repos.MusicRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.publisher.toMono

@Service
class ListingServiceImpl(
    private val listingRepository: ListingRepository,
    private val listingMusicRepository: ListingMusicRepository,
    private val musicRepository: MusicRepository,
    private val artistRepository: ArtistRepository,
) : ListingService {

    override fun getAllListings(): Flux<ListingDto>? {
        return listingRepository.findAll().flatMap { listing -> fetchListingTree(listing) }
    }

    override fun getListingById(id: Long): Mono<ListingDto> {
        return listingRepository.findById(id).flatMap { listing -> fetchListingTree(listing) }
    }

    override fun createListing(listingDto: ListingDto): Mono<ListingDto> {
        return listingRepository.save(ListingConverter.toListing(listingDto)).flatMap {
            val referenceMusicId = it.referenceMusicId
            val artistId = it.artistId
            Mono.zip(
                if (referenceMusicId == null) Mono.empty() else musicRepository.findById(referenceMusicId)
                    .subscribeOn(Schedulers.parallel()),
                if (artistId == null) Mono.empty() else artistRepository.findById(artistId)
                    .subscribeOn(Schedulers.parallel())
            ) { music, artist ->
                ListingConverter.toListingDto(
                    it,
                    ArtistConverter.toArtistDto(artist),
                    MusicConverter.toMusicDto(music)
                )
            }.subscribeOn(Schedulers.parallel())
        }.flatMapMany { it ->
            val listingId = it.id
            Flux.fromIterable(listingDto.musicDtos)
                .map {
                    when (val id = it?.id) {
                        null -> Mono.empty()
                        else -> listingMusicRepository.save(ListingMusic(null, listingId, id))
                    }
                }
        }.flatMap { it }.map {
            ListingDto(it.listingId, listingDto.artistDto, listingDto.referenceMusicDto, listingDto.musicDtos)
        }.toMono()
    }

    private fun fetchListingTree(listing: Listing): Mono<ListingDto> {
        val referenceMusicId = listing.referenceMusicId
        val artistId = listing.artistId
        return Mono.zip(
            (if (referenceMusicId == null) Mono.empty() else musicRepository.findById(referenceMusicId)).subscribeOn(
                Schedulers.parallel()),
            (if (artistId == null) Mono.empty() else artistRepository.findById(artistId)).subscribeOn(Schedulers.parallel())
        ) { music, artist ->
            ListingConverter.toListingDto(
                listing,
                ArtistConverter.toArtistDto(artist),
                MusicConverter.toMusicDto(music)
            )
        }.subscribeOn(Schedulers.parallel())
            .flatMap { listingDto ->
                listingDto.id?.let { it1 ->
                    listingMusicRepository
                        .findByListingId(it1).map { it.musicId }
                }
                    ?.flatMap { musicId ->
                        musicRepository.findById(musicId)
                            .map { MusicConverter.toMusicDto(it) }
                    }?.map {
                        listingDto.musicDtos?.add(it)
                        listingDto
                    }
            }.toMono()
    }
}