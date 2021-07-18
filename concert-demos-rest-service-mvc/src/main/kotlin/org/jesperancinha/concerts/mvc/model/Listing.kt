package org.jesperancinha.concerts.mvc.model

import javax.persistence.*

@Entity
data class Listing(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @OneToOne
    val artist: Artist,

    @OneToOne
    val referenceMusic: Music,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "listing_music",
        joinColumns = [JoinColumn(name = "music_id")],
        inverseJoinColumns = [JoinColumn(name = "listing_id")]
    )
    var musics: MutableSet<Music> = HashSet(),

    @ManyToMany(mappedBy = "listings")
    val concerts: MutableSet<Concert> = HashSet(),
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
        var result = id.hashCode()
        result = 31 * result + artist.hashCode()
        result = 31 * result + referenceMusic.hashCode()
        result = 31 * result + musics.hashCode()
        return result
    }
}