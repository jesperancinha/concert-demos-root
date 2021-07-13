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
import reactor.core.publisher.toMono
import reactor.core.scheduler.Schedulers

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
            Mono.zip(
                musicRepository.findById(it.referenceMusicId).subscribeOn(Schedulers.parallel()),
                artistRepository.findById(it.artistId).subscribeOn(Schedulers.parallel())
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
                .map { listingMusicRepository.save(ListingMusic(null, listingId, it?.id)) }
        }.flatMap { it }.map {
            listingDto.id = it.listingId
            listingDto
        }.toMono()
    }

    private fun fetchListingTree(listing: Listing): Mono<ListingDto> {
        return Mono.zip(
            musicRepository.findById(listing.referenceMusicId).subscribeOn(Schedulers.parallel()),
            artistRepository.findById(listing.artistId).subscribeOn(Schedulers.parallel())
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
            }
    }
}