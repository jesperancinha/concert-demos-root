package org.jesperancinha.concerts.model

import javax.persistence.*

@Entity
data class Concert(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = 0,
        val name: String? = null,
        val location: String? = null,
        val date: String? = null
) {
    @OneToMany(mappedBy = "concert", cascade = [CascadeType.ALL],
            fetch = FetchType.LAZY, orphanRemoval = true)
    var listings: MutableSet<Listing>? = HashSet()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Concert

        if (id != other.id) return false
        if (name != other.name) return false
        if (location != other.location) return false
        if (date != other.date) return false
        if (listings != other.listings) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (location?.hashCode() ?: 0)
        result = 31 * result + (date?.hashCode() ?: 0)
        result = 31 * result + listings.hashCode()
        return result
    }


}