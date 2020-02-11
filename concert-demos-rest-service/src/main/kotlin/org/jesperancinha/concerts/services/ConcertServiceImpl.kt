package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.converters.ArtistConverter
import org.jesperancinha.concerts.converters.ConcertConverter
import org.jesperancinha.concerts.converters.ListingConverter
import org.jesperancinha.concerts.converters.MusicConverter
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.model.ConcertListing
import org.jesperancinha.concerts.model.Listing
import org.jesperancinha.concerts.repos.*
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.zip
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono

@Service
class ConcertServiceImpl(
        private val concertRepository: ConcertRepository,
        private val concertListingRepository: ConcertListingRepository,
        private val listingRepository: ListingRepository,
        private val artistRepository: ArtistRepository,
        private val musicRepository: MusicRepository
) : ConcertService {
    override fun getAllConcerts(): Flux<ConcertDto>? {
        return concertRepository.findAll()
                .map {
                    it.id?.let(getConcertListingPublisher(ConcertConverter.toConcertDto(it)))
                }
                .flatMap { it }
                .map {
                    getListingPublisher(it)
                }
                .flatMap { it }
                .map {
                    zip(getArtistPublisher(it),
                            getMusicPublisher(it)
                    ) { artistDto, musicDto-> Pair(artistDto, musicDto)}

                }
                .flatMap { it }
                .map {
                    val artistDto: ArtistDto = it.first.second
                    val musicDto: MusicDto = it.second.second
                    val listingLink = it.first
                    val listing = listingLink.first.second
                    val concertLink = listingLink.first
                    val concertDto: ConcertDto = concertLink.first.first
                    listing.id?.let { (concertDto.listingDtos.add(ListingConverter.toListingDto(listing, artistDto, musicDto ))) }
                    concertDto
                }
    }

    private fun getArtistPublisher(listingLink: Pair<Pair<ConcertDto, ConcertListing>, Listing>): Mono<Pair<Pair<Pair<ConcertDto, ConcertListing>, Listing>, ArtistDto>> =
            artistRepository.findById(listingLink.second.artistId)
                    .map { Pair(listingLink, ArtistConverter.toArtistDto(it)) }

    private fun getMusicPublisher(listingLink: Pair<Pair<ConcertDto, ConcertListing>, Listing>): Mono<Pair<Pair<Pair<ConcertDto, ConcertListing>, Listing>, MusicDto>> =
            musicRepository.findById(listingLink.second.referenceMusicId)
                    .map { Pair(listingLink, MusicConverter.toMusicDto(it)) }

    private fun getListingPublisher(concertLink: Pair<ConcertDto, ConcertListing>): Flux<Pair<Pair<ConcertDto, ConcertListing>, Listing>> =
            listingRepository.findById(concertLink.second.listingId)
                    .map { Pair(concertLink, it) }
                    .toFlux()

    private fun getConcertListingPublisher(concertDto: ConcertDto): (Long) -> Flux<Pair<ConcertDto, ConcertListing>> = { concertId ->
        concertListingRepository.findByConcertId(concertId)
                .map { Pair(concertDto, it) }
                .toFlux()
    }

    override fun createConcert(concert: ConcertDto): Mono<ConcertDto> {
        return concertRepository.save(ConcertConverter.toConcert(concert)).map { ConcertConverter.toConcertDto(it) }
    }
}