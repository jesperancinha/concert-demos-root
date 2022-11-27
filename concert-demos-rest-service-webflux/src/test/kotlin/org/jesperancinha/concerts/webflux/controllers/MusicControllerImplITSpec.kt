package org.jesperancinha.concerts.webflux.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.webflux.configuration.ConfigurationProperties
import org.jesperancinha.concerts.webflux.controllers.TestConstants.Companion.HEY_MAMA
import org.jesperancinha.concerts.webflux.model.Music
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

@SpringBootTest(webEnvironment = RANDOM_PORT)
@EnableConfigurationProperties(ConfigurationProperties::class)
@ActiveProfiles("test")
class MusicControllerImplITSpec {

    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var artistService: ArtistService

    @Autowired
    lateinit var musicRepository: MusicRepository


    @Test
    fun `should GetAllMusics`() {
        val uri = "http://localhost:${port}/concerts/data/musics"
        val restTemplate = RestTemplate()
        val result = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<Music>>() {}
        ).body
        result.shouldBeEmpty()
    }

    @Test
    fun `should CreateMusic`() {
        val uri = "http://localhost:${port}/concerts/data/musics"
        val musicDto = MusicDto(
            null,
            "Hey mama",
            HEY_MAMA
        )
        val restTemplate = RestTemplate()
        restTemplate.postForEntity(uri, musicDto, Music::class.java)

        val result = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<Music>>() {}
        ).body

        result.shouldNotBeNull()
        result.shouldNotBeEmpty()
        result.shouldHaveSize(1)
        val music = result[0]
        music.shouldNotBeNull()
        music.id shouldNotBe 0
        music.name shouldBe "Hey mama"
        music.lyrics shouldBe HEY_MAMA
    }

    @BeforeEach
    fun setup() {
        musicRepository.deleteAll().block()
    }
}
