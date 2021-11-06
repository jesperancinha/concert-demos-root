package org.jesperancinha.concerts.mvc.model

import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.types.Gender
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Artist(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String,
    val gender: Gender,
    val careerStart: Long,
    val birthDate: String,
    val birthCity: String,
    val country: String,
    val keywords: String,
)

fun Artist.toArtistDto(): ArtistDto {
    return ArtistDto(
        id = this.id,
        name = this.name,
        gender = this.gender,
        careerStart = this.careerStart,
        birthDate = this.birthDate,
        birthCity = this.birthCity,
        country = this.country,
        keywords = this.keywords
    )
}
fun ArtistDto.toArtist(): Artist {
    return Artist(
        this.id,
        this.name,
        this.gender,
        this.careerStart,
        this.birthDate,
        this.birthCity,
        this.country,
        this.keywords
    )
}