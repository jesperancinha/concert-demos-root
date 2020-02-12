package org.jesperancinha.concerts.data

import org.jesperancinha.concerts.types.Gender

data class ArtistDto(
        var id: Long? = null,
        val name: String? = null,
        val gender: Gender? = null,
        val careerStart: Long? = null,
        val birthDate: String? = null,
        val birthCity: String? = null,
        val country: String? = null,
        val keywords: String? = null

) {
    constructor(name: String,
                gender: Gender,
                careerStart: Long,
                birthDate: String,
                birthCity: String,
                country: String,
                keywords: String) :
            this(
                    null,
                    name,
                    gender,
                    careerStart,
                    birthDate,
                    birthCity,
                    country,
                    keywords)


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
