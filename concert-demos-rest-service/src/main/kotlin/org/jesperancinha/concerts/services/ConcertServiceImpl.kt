package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.converters.ArtistConverter
import org.jesperancinha.concerts.converters.ConcertConverter
import org.jesperancinha.concerts.converters.ListingConverter
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.model.ConcertListing
import org.jesperancinha.concerts.model.Listing
import org.jesperancinha.concerts.repos.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux

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
                    getArtistPublisher(it)
                }
                .flatMap { it }
                .map {
                    val artistDto: ArtistDto = it.second
                    val listingLink = it.first
                    val listing = listingLink.second
                    val concertLink = listingLink.first
                    val concertDto: ConcertDto = concertLink.first
                    val listingDto = ListingConverter.toListingDto(listing, artistDto, null)
                    concertDto.listingDtos.add(listingDto)
                    concertDto
                }
    }

    private fun getArtistPublisher(listingLink: Pair<Pair<ConcertDto, ConcertListing>, Listing>): Flux<Pair<Pair<Pair<ConcertDto, ConcertListing>, Listing>, ArtistDto>> =
            artistRepository.findById(listingLink.second.artistId)
                    .map { Pair(listingLink, ArtistConverter.toArtistDto(it)) }
                    .toFlux()

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