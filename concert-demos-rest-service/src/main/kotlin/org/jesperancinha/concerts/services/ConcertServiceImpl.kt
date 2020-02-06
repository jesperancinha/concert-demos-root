package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.model.Concert
import org.jesperancinha.concerts.repos.ConcertRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class ConcertServiceImpl(private val concertRepository: ConcertRepository) : ConcertService {
    override fun getAllConcerts(): Flux<Concert?>? {
        return concertRepository.findAll()
    }

    override fun createConcert(concert: Concert): Mono<Concert> {
        return concertRepository.save(concert)
    }
}