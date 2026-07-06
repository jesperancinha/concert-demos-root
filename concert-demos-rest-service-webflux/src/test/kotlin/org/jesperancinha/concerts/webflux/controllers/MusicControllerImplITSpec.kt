package org.jesperancinha.concerts.webflux.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.webflux.configuration.ConfigurationProperties
import org.jesperancinha.concerts.webflux.controllers.TestConstants.Companion.LYRICS_TEXT
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
import org.springframework.web.client.postForEntity

@SpringBootTest(webEnvironment = RANDOM_PORT)
@EnableConfigurationProperties(ConfigurationProperties::class)
@ActiveProfiles("test")
class MusicControllerImplITSpec @Autowired constructor(
    private val artistService: ArtistService,
    private val musicRepository: MusicRepository
) {

    @LocalServerPort
    var port: Int = 0

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
            LYRICS_TEXT
        )
        val restTemplate = RestTemplate()
        restTemplate.postForEntity<Music>(uri, musicDto)

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
        music.lyrics shouldBe LYRICS_TEXT
    }

    @BeforeEach
    fun setup() {
        musicRepository.deleteAll().block()
    }
}
