package org.jesperancinha.concerts.webflux.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.assertj.core.api.SoftAssertions
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.types.Gender
import org.jesperancinha.concerts.webflux.configuration.ConfigurationProperties
import org.jesperancinha.concerts.webflux.controllers.TestConstants.HEY_MAMA
import org.jesperancinha.concerts.webflux.model.Artist
import org.jesperancinha.concerts.webflux.model.Concert
import org.jesperancinha.concerts.webflux.repos.ArtistRepository
import org.jesperancinha.concerts.webflux.repos.ConcertRepository
import org.jesperancinha.concerts.webflux.repos.ListingRepository
import org.jesperancinha.concerts.webflux.repos.MusicRepository
import org.jesperancinha.concerts.webflux.services.ArtistService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = RANDOM_PORT)
@EnableConfigurationProperties(ConfigurationProperties::class)
@ActiveProfiles("test")
class ConcertControllerImplITSpec {

    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var artistService: ArtistService

    @Autowired
    lateinit var listingRepository: ListingRepository

    @Autowired
    lateinit var artistRepository: ArtistRepository

    @Autowired
    lateinit var musicRepository: MusicRepository

    @Autowired
    lateinit var concertRepository: ConcertRepository

    @Test
    fun `should GetAllConcerts`() {
        val uri = "http://localhost:${port}/concerts/data/listings"

        val restTemplate = RestTemplate()
        val result = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<Concert>>() {}
        ).body
        result.shouldBeEmpty()
    }

    @Test
    fun `should CreateConcert`() {
        val artistsUri = "http://localhost:${port}/concerts/data/artists"
        val musicsUri = "http://localhost:${port}/concerts/data/musics"
        val listingsUri = "http://localhost:${port}/concerts/data/listings"
        val concertsUri = "http://localhost:${port}/concerts/data/concerts"

        val musicDto = MusicDto(
            null,
            "Hey mama",
            HEY_MAMA
        )
        val artistDto = ArtistDto(
            null,
            "Nicky Minaj",
            Gender.FEMALE,
            1000L,
            LocalDateTime.now().toString(),
            "Port of Spain",
            "Trinidad en Tobago",
            "Rap"
        )

        val restTemplate = RestTemplate()
        val savedArtistDto = restTemplate. postForEntity(artistsUri, artistDto, ArtistDto::class.java).body
            val savedMusicDto = restTemplate.postForEntity (musicsUri, musicDto, MusicDto::class.java).body
        val listingDto =  ListingDto (
                null,
        savedArtistDto,
        savedMusicDto,
        mutableListOf(savedMusicDto)
        )
        val savedListingDto = restTemplate . postForEntity (listingsUri, listingDto, ListingDto::class.java).body
        val concertDto =  ConcertDto(
            null,
            "Nicki Wrld Tour",
            "Amsterdam",
            LocalDateTime.of(2019, 3, 25, 0, 0, 0).toString(),
            mutableListOf(savedListingDto)

        )
        val savedConcertDto = restTemplate . postForEntity (concertsUri, concertDto, ConcertDto::class.java).body
            val result = restTemplate.exchange(
                concertsUri,
        HttpMethod.GET,
        null,
        object : ParameterizedTypeReference<List<ConcertDto>>() {}
        ).body

        result.shouldNotBeNull()
        result.shouldNotBeEmpty()
        result.shouldHaveSize(1)
        val concertDtoResult = result [0]
        concertDtoResult.id shouldNotBe 0
        concertDtoResult.id shouldBe  savedConcertDto.id
        concertDtoResult.name shouldBe "Nicki Wrld Tour"
        concertDtoResult.location shouldBe "Amsterdam"
       val listings = concertDtoResult.listingDtos
        listings.shouldNotBeNull()
        listings.shouldHaveSize(1)
        val listingDtoResult = listings[0]
        listingDtoResult.shouldNotBeNull()
        listingDtoResult.artistDto shouldBe savedArtistDto
        listingDtoResult.referenceMusicDto shouldBe savedMusicDto
        listingDtoResult.musicDtos.shouldHaveSize(1)
        listingDtoResult.musicDtos[0] shouldBe savedMusicDto
    }

    @BeforeEach
    fun setup()
    {
        artistRepository.deleteAll().block()
        musicRepository.deleteAll().block()
        listingRepository.deleteAll().block()
        concertRepository.deleteAll().block()
    }
}
