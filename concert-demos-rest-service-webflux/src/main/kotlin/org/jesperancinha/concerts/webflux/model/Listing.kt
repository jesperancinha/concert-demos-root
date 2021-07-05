package org.jesperancinha.concerts.webflux.model

data class Listing(
        var id: Long? = null,
        val artistId: Long,
        val referenceMusicId: Long,
)