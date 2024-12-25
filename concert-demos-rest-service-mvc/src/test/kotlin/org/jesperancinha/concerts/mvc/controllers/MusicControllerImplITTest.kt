package org.jesperancinha.concerts.mvc.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.LYRICS_TEXT
import org.jesperancinha.concerts.mvc.daos.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import java.net.URI

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class MusicControllerImplITTest(
    @LocalServerPort
    val port: Int,

    @Autowired
    val listingRepository: ListingRepository,

    @Autowired
    val artistRepository: ArtistRepository,

    @Autowired
    val musicRepository: MusicRepository,

    @Autowired
    val concertRepository: ConcertRepository,
) {

    @Test
    fun `retrieve all music`() {
        val uri = "http://localhost:${port}/concerts/data/musics"
        val restTemplate = RestTemplate()
        val request = RequestEntity<Any>(HttpMethod.GET, URI.create(uri))
        val respType = object : ParameterizedTypeReference<List<MusicDto>>() {}
        val response = restTemplate.exchange(request, respType)
        val result: List<MusicDto> = response.body ?: listOf()
        result.shouldBeEmpty()
    }

    @Test
    fun `create music`() {
        val uri = "http://localhost:${port}/concerts/data/musics"
        val musicDto = MusicDto(
            name = "Hey mama",
            lyrics = LYRICS_TEXT)
        val restTemplate = RestTemplate()
        restTemplate.postForEntity(uri, musicDto, Music::class.java)
        val request = RequestEntity<Any>(HttpMethod.GET, URI.create(uri))
        val respType = object : ParameterizedTypeReference<List<MusicDto>>() {}
        val response = restTemplate.exchange(request, respType)
        val result: List<MusicDto> = response.body ?: listOf()
        result.shouldNotBeEmpty()
        result.shouldHaveSize(1)
        val musicDto1 = result[0]
        musicDto1.id shouldNotBe 0
        musicDto1.name shouldBe "Hey mama"
        musicDto1.lyrics shouldBe LYRICS_TEXT
    }

    @BeforeEach
    fun setup() {
        concertRepository.deleteAll()
        listingRepository.deleteAll()
        artistRepository.deleteAll()
        musicRepository.deleteAll()
    }
}
