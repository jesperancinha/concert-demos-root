package org.jesperancinha.concerts.converters

import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.model.Concert

object ConcertConverter {
    fun toConcertDto(concert: Concert, listings: List<ListingDto>): ConcertDto {
        return ConcertDto(
                concert.id,
                concert.name,
                concert.location,
                concert.date,
                listings
        )
    }
}