package org.jesperancinha.concerts.webflux.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.types.Gender.FEMALE
import org.jesperancinha.concerts.webflux.configuration.ConfigurationProperties
import org.jesperancinha.concerts.webflux.controllers.TestConstants.Companion.HEY_MAMA
import org.jesperancinha.concerts.webflux.model.Listing
import org.jesperancinha.concerts.webflux.repos.ArtistRepository
import org.jesperancinha.concerts.webflux.repos.ListingRepository
import org.jesperancinha.concerts.webflux.repos.MusicRepository
import org.jesperancinha.concerts.webflux.services.ListingService
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
class ListingControllerImplITSpec {

    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var artistService: ListingService

    @Autowired
    lateinit var listingRepository: ListingRepository

    @Autowired
    lateinit var artistRepository: ArtistRepository

    @Autowired
    lateinit var musicRepository: MusicRepository


    @Test
    fun `should GetAllListings`() {
        val uri = "http://localhost:${port}/concerts/data/listings"
        val restTemplate = RestTemplate()
        val result = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<Listing>>() {}
        ).body
        result.shouldBeEmpty()
    }

    @Test
    fun `should CreateListing`() {
        val artistsUri = "http://localhost:${port}/concerts/data/artists"
        val musicsUri = "http://localhost:${port}/concerts/data/musics"
        val listingsUri = "http://localhost:${port}/concerts/data/listings"
        val musicDto = MusicDto(
            null,
            "Hey mama",
            HEY_MAMA
        )
        val artistDto = ArtistDto(
            null,
            "Nicky Minaj",
            FEMALE,
            1000L,
            LocalDateTime.now().toString(),
            "Port of Spain",
            "Trinidad en Tobago",
            "Rap"
        )
        val restTemplate = RestTemplate()

        val savedArtistDto = restTemplate.postForEntity(artistsUri, artistDto, ArtistDto::class.java).body
        val savedMusicDto = restTemplate.postForEntity(musicsUri, musicDto, MusicDto::class.java).body
        savedArtistDto.shouldNotBeNull()
        savedMusicDto.shouldNotBeNull()
        val listingDto = ListingDto(
            null,
            savedArtistDto,
            savedMusicDto,
            mutableListOf(savedMusicDto)
        )
        val savedListingDto = restTemplate.postForEntity(listingsUri, listingDto, ListingDto::class.java).body
        val result = restTemplate.exchange(
            listingsUri,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<ListingDto>>() {}
        ).body
        savedListingDto.shouldNotBeNull()
        result.shouldNotBeNull()
        result.shouldNotBeEmpty()
        result.shouldHaveSize(1)
        val listing0 = result[0]
        listing0.id shouldNotBe 0
        listing0.id shouldBe savedListingDto.id
        listing0.artistDto shouldBe artistDto
        listing0.referenceMusicDto shouldBe musicDto
        listing0.musicDtos.shouldHaveSize(1)
        listing0.musicDtos[0].shouldBe(musicDto)
    }

    @BeforeEach
    fun setup() {
        artistRepository.deleteAll().block()
        musicRepository.deleteAll().block()
        listingRepository.deleteAll().block()
    }
}
