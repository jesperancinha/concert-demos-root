package org.jesperancinha.concerts.model

import java.time.LocalDateTime


data class Artist(
        val name: String,
        val careerStart: Long,
        val birthDate: LocalDateTime,
        val keywords: Array<String>

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Artist

        if (name != other.name) return false
        if (careerStart != other.careerStart) return false
        if (birthDate != other.birthDate) return false
        if (!keywords.contentEquals(other.keywords)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + careerStart.hashCode()
        result = 31 * result + birthDate.hashCode()
        result = 31 * result + keywords.contentHashCode()
        return result
    }
}
