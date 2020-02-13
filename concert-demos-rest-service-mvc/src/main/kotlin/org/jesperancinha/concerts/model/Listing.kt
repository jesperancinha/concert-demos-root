package org.jesperancinha.concerts.model

import javax.persistence.*

@Entity
data class Listing(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,


        @OneToOne
        val artist: Artist? = null,

        @OneToOne
        val referenceMusic: Music? = null,

        @OneToMany(mappedBy = "listing")
        val musics: MutableSet<Music>? = HashSet(),

        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinColumn(name = "concert_id")
        var concert: Concert? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Listing

        if (id != other.id) return false
        if (artist != other.artist) return false
        if (referenceMusic != other.referenceMusic) return false
        if (musics != other.musics) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (artist?.hashCode() ?: 0)
        result = 31 * result + (referenceMusic?.hashCode() ?: 0)
        result = 31 * result + musics.hashCode()
        return result
    }
}