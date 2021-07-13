package org.jesperancinha.concerts.webflux.services

import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.webflux.converters.ConcertConverter
import org.jesperancinha.concerts.webflux.model.ConcertListing
import org.jesperancinha.concerts.webflux.repos.ConcertListingRepository
import org.jesperancinha.concerts.webflux.repos.ConcertRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@Service
class ConcertServiceImpl(
    private val concertRepository: ConcertRepository,
    private val concertListingRepository: ConcertListingRepository,
    private val listingService: ListingService

) : ConcertService {
    override fun getAllConcerts(): Flux<ConcertDto> {
        return concertRepository.findAll()
            .map { ConcertConverter.toConcertDto(it) }
            .flatMap { concert ->
                concertListingRepository.findByConcertId(concert.id)
                    .flatMap { listingService.getListingById(it.listingId) }
                    .map {
                        concert.listingDtos?.add(it)
                        concert
                    }
            }
    }

    override fun createConcert(concertDto: ConcertDto): Mono<ConcertDto> {
        return concertRepository.save(ConcertConverter.toConcert(concertDto))
            .flatMapMany {
                concertDto.id = it.id
                concertDto.listingDtos?.let { it1 -> Flux.fromIterable(it1) }
            }.flatMap {
                concertListingRepository.save(ConcertListing(concertDto.id, it?.id))
            }.map { concertDto }
            .toMono()
    }
}