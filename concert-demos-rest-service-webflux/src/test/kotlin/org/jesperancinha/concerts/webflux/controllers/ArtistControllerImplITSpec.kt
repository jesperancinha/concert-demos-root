package org.jesperancinha.concerts.webflux.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.types.Gender.AGENDER
import org.jesperancinha.concerts.webflux.configuration.ConfigurationProperties
import org.jesperancinha.concerts.webflux.model.Artist
import org.jesperancinha.concerts.webflux.repos.ArtistRepository
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
class ArtistControllerImplITSpec{
    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var artistRepository: ArtistRepository

    @Test
    fun `should GetAllArtists`() {
        val uri = "http://localhost:${port}/concerts/data/artists"
        val restTemplate = RestTemplate()

        val result = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<Artist>>() {}
        ).body
        result.shouldBeEmpty()
    }

   @Test
   fun `should CreateArtist`() {
        val uri = "http://localhost:${port}/concerts/data/artists"
        val artist =  ArtistDto(null,
                "Duran Duran",
            AGENDER,
                1000L,
                LocalDateTime.now().toString(),
                "Birmingham",
                "Great Britain",
                "test")
        val restTemplate =  RestTemplate()
        restTemplate.postForEntity(uri, artist, Artist::class.java)
            val result = restTemplate.exchange(
                uri,
        HttpMethod.GET,
        null,
        object : ParameterizedTypeReference<List<Artist>>() {}
        ).body
        result.shouldNotBeNull()
        result.shouldNotBeEmpty()
        result.shouldHaveSize(1)
        val artist0 = result[0]
        artist0.apply {
            id shouldNotBe 0
            name shouldBe "Duran Duran"
            gender shouldBe AGENDER
            birthCity shouldBe "Birmingham"
        }
    }

    @BeforeEach
    fun setup() {
        artistRepository.deleteAll().block()
    }
}
