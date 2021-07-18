package org.jesperancinha.concerts.mvc.model

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