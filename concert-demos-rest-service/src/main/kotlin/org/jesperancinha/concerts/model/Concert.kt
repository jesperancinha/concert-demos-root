package org.jesperancinha.concerts.model

import java.time.LocalDateTime

data class Concert(
        val name: String,
        val location: String,
        val date: LocalDateTime,
        val listings: List<Listing>
) {
}