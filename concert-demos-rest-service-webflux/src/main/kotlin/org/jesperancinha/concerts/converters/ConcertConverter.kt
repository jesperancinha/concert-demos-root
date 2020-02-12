package org.jesperancinha.concerts.converters

import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.model.Concert

object ConcertConverter {
    fun toConcertDto(concert: Concert): ConcertDto {
        return ConcertDto(
                concert.id,
                concert.name,
                concert.location,
                concert.date
        )
    }

    fun toConcert(concert: ConcertDto): Concert {
        return Concert(null, concert.name!!, concert.location!!, concert.date!!)
    }
}