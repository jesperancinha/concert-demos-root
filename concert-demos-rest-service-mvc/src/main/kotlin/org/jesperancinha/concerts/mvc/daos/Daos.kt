package org.jesperancinha.concerts.mvc.daos

import org.hibernate.Hibernate
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.types.Gender
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import javax.persistence.*

@Entity
data class Concert(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,
    val name: String,
    val location: String,
    val date: String,
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "concert_listing",
        joinColumns = [JoinColumn(name = "listing_id")],
        inverseJoinColumns = [JoinColumn(name = "concert_id")]
    )
    var listings: MutableSet<Listing>? = HashSet(),
)

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

@Entity
data class Music(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val name: String,

    val lyrics: String,

    @ManyToMany(mappedBy = "musics")
    var listings: MutableSet<Listing> = mutableSetOf(),
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Music

        if (id != other.id) return false
        if (name != other.name) return false
        if (lyrics != other.lyrics) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + lyrics.hashCode()
        return result
    }

}

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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Artist

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , gender = $gender , careerStart = $careerStart , birthDate = $birthDate , birthCity = $birthCity , country = $country , keywords = $keywords )"
    }

}

fun Artist.toArtistDto(): ArtistDto = ArtistDto(
    id = this.id,
    name = this.name,
    gender = this.gender,
    careerStart = this.careerStart,
    birthDate = this.birthDate,
    birthCity = this.birthCity,
    country = this.country,
    keywords = this.keywords
)

fun ArtistDto.toArtist(): Artist = Artist(
    this.id,
    this.name,
    this.gender,
    this.careerStart,
    this.birthDate,
    this.birthCity,
    this.country,
    this.keywords
)

fun Music.toMusicDto(): MusicDto = MusicDto(
    this.id,
    this.name,
    this.lyrics
)


fun MusicDto.toMusic(): Music = Music(
    this.id,
    this.name,
    this.lyrics
)



interface ArtistRepository : CrudRepository<Artist, Long>
interface ConcertRepository : CrudRepository<Concert, Long>
interface ListingRepository : CrudRepository<Listing, Long>
interface MusicRepository : CrudRepository<Music, Long>

@Component
class ConcertConverter(
    private val listingConverter: ListingConverter,
    private val listingRepository: ListingRepository,
) {
    fun toConcertDto(concert: Concert): ConcertDto {
        return ConcertDto(
            concert.id,
            concert.name,
            concert.location,
            concert.date,
            concert.listings?.map { listingConverter.toListingDto(it) }?.toMutableList()
        )
    }

    fun toConcert(concertDto: ConcertDto): Concert {
        val concert = Concert(concertDto.id, concertDto.name, concertDto.location, concertDto.date)
        concert.listings = concertDto.listingDtos?.
        map {
            val listing = listingRepository.findById(it?.id ?: -1).orElse(null)
            listing.concerts.add(concert)
            listing
        }?.toMutableSet()
        return concert
    }
}

@Component
class ListingConverter(
    private val musicRepository: MusicRepository,

    ) {
    fun toListingDto(listing: Listing): ListingDto {
        return ListingDto(
            listing.id,
            listing.artist.toArtistDto(),
            listing.referenceMusic.toMusicDto(),
            listing.musics.map { it.toMusicDto() }.toMutableList()
        )

    }

    fun toListing(listingDto: ListingDto): Listing {
        return Listing(
            listingDto.id,
            listingDto.artistDto.toArtist(),
            listingDto.referenceMusicDto.toMusic()
        )
    }

    fun toFullListing(listingDto: ListingDto): Listing {
        val listing = Listing(
            listingDto.id,
            listingDto.artistDto.toArtist(),
            listingDto.referenceMusicDto.toMusic()
        )
        listing.musics = listingDto.musicDtos.map {
            val music = musicRepository.findById(it?.id ?: -1).orElse(null)
            music.listings.add(listing)
            musicRepository.save(music)
        }.toMutableSet()
        return listing
    }
}