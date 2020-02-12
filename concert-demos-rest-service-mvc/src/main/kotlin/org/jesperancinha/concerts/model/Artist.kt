package org.jesperancinha.concerts.model

import org.jesperancinha.concerts.types.Gender
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Artist(

        @Id @GeneratedValue
        var id: Long? = null,
        val name: String? = null,
        val gender: Gender? = null,
        val careerStart: Long? = null,
        val birthDate: String? = null,
        val birthCity: String? = null,
        val country: String? = null,
        val keywords: String? = null

) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Artist

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
