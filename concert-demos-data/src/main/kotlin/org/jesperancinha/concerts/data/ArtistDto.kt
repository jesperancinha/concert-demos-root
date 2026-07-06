package org.jesperancinha.concerts.data

import org.jesperancinha.concerts.types.Gender
import java.util.Objects

data class ArtistDto(
    val id: Long? = null,
    val name: String,
    val gender: Gender,
    val careerStart: Long,
    val birthDate: String,
    val birthCity: String,
    val country: String,
    val keywords: String
    ) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArtistDto

        if (name != other.name) return false
        if (gender != other.gender) return false
        if (careerStart != other.careerStart) return false
        if (birthDate != other.birthDate) return false
        if (birthCity != other.birthCity) return false
        if (country != other.country) return false
        if (keywords != other.keywords) return false

        return true
    }

    override fun hashCode(): Int =
        Objects.hash(name, gender, careerStart, birthDate, birthCity, country, keywords)

}
