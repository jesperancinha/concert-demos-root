package org.jesperancinha.concerts.mvc.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import jakarta.transaction.Transactional
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.mvc.daos.Artist
import org.jesperancinha.concerts.mvc.daos.ArtistRepository
import org.jesperancinha.concerts.mvc.daos.ConcertRepository
import org.jesperancinha.concerts.mvc.daos.ListingRepository
import org.jesperancinha.concerts.mvc.daos.MusicRepository
import org.jesperancinha.concerts.types.Gender.AGENDER
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.client.postForEntity
import java.net.URI
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class ArtistControllerITTest(
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
    fun `Retrieve list of all artists`() {
        val uri = "http://localhost:${port}/concerts/data/artists"
        val restTemplate = RestTemplate()
        val result: List<Artist> = restTemplate.getForObject(uri, List::class)
        result.shouldBeEmpty()
    }

    @Test
    @Transactional
    fun `Create an artist`() {
        val uri = "http://localhost:${port}/concerts/data/artists"
        val artistDto = ArtistDto(
            name = "Duran Duran",
            gender = AGENDER,
            careerStart = 1000L,
            birthDate = LocalDateTime.now().toString(),
            birthCity = "Birmingham",
            country = "Great Britain",
            keywords = "test"
        )
        val restTemplate = RestTemplate()
        val test: ResponseEntity<Unit> = restTemplate.postForEntity(uri, artistDto, Artist::class)

        test shouldNotBe null
        test.statusCode shouldBe HttpStatus.OK

        val request = RequestEntity<Any>(HttpMethod.GET, URI.create(uri))
        val respType = object : ParameterizedTypeReference<List<Artist>>() {}
        val response = restTemplate.exchange(request, respType)
        val result: List<Artist> = response.body ?: listOf()

        result.shouldNotBeEmpty()
        result shouldHaveSize 1

        val artistResult = result[0]
        artistResult.id shouldNotBe 0
        artistDto.name shouldBe "Duran Duran"
        artistDto.gender shouldBe AGENDER
        artistDto.birthCity shouldBe "Birmingham"
    }

    @BeforeEach
    fun setup() {
        concertRepository.deleteAll()
        listingRepository.deleteAll()
        artistRepository.deleteAll()
        musicRepository.deleteAll()
    }
}

