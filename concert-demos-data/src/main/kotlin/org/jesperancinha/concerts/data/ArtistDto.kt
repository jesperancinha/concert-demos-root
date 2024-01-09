package org.jesperancinha.concerts.data

import org.jesperancinha.concerts.types.Gender

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

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + gender.hashCode()
        result = 31 * result + careerStart.hashCode()
        result = 31 * result + birthDate.hashCode()
        result = 31 * result + birthCity.hashCode()
        result = 31 * result + country.hashCode()
        result = 31 * result + keywords.hashCode()
        return result
    }

}
