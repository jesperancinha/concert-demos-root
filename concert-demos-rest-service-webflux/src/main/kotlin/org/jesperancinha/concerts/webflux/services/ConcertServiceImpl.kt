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
    private val listingService: ListingService,

    ) : ConcertService {
    override fun getAllConcerts(): Flux<ConcertDto> {
        return concertRepository.findAll()
            .map { ConcertConverter.toConcertDto(it) }
            .flatMap { concert ->
                when (val concertId = concert.id) {
                    null -> Flux.empty()
                    else -> {
                        concertListingRepository.findByConcertId(concertId)
                            .flatMap {
                                it.listingId.let { id ->
                                    when (id) {
                                        null -> Mono.empty()
                                        else -> listingService.getListingById(id)
                                    }
                                }
                            }
                            .map {
                                concert.listingDtos?.add(it)
                                concert
                            }

                    }
                }
            }
    }

    override fun createConcert(concertDto: ConcertDto): Mono<ConcertDto> {
        var concertId: Long? = null
        return concertRepository.save(ConcertConverter.toConcert(concertDto))
            .flatMapMany {
                concertId = it.id
                ConcertDto(it.id, concertDto.name, concertDto.date, concertDto.location, concertDto.listingDtos)
                    .listingDtos?.let { it1 -> Flux.fromIterable(it1) }
            }.flatMap {
                concertListingRepository.save(ConcertListing(concertId, it?.id))
            }.map {
                ConcertDto(concertId,
                    concertDto.name,
                    concertDto.location,
                    concertDto.date,
                    concertDto.listingDtos)
            }
            .toMono()
    }
}