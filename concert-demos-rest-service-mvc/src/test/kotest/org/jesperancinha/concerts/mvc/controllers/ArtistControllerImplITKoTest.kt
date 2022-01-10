package org.jesperancinha.concerts.mvc.controllers

import io.kotest.core.spec.style.WordSpec
import io.kotest.core.test.TestCase
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.mvc.model.Artist
import org.jesperancinha.concerts.mvc.repos.ArtistRepository
import org.jesperancinha.concerts.mvc.repos.ConcertRepository
import org.jesperancinha.concerts.mvc.repos.ListingRepository
import org.jesperancinha.concerts.mvc.repos.MusicRepository
import org.jesperancinha.concerts.types.Gender.AGENDER
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.core.ParameterizedTypeReference
import org.springframework.core.env.Environment
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
import javax.transaction.Transactional
import kotlin.properties.Delegates

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class ArtistControllerImplITKoTest : WordSpec() {

    @Autowired
    lateinit var environment: Environment

    @Autowired
    lateinit var listingRepository: ListingRepository

    @Autowired
    lateinit var artistRepository: ArtistRepository

    @Autowired
    lateinit var musicRepository: MusicRepository

    @Autowired
    lateinit var concertRepository: ConcertRepository

    final var port by Delegates.notNull<Int>()

    override fun extensions() = listOf(SpringExtension)

    init {
        "artist controller extended integration tests" should {
            "retrieve list of all artists" {
                val uri = "http://localhost:${port}/concerts/data/artists"
                val restTemplate = RestTemplate()
                val result: List<Artist> = restTemplate.getForObject(uri, List::class)
                result.shouldBeEmpty()
            }
            "create an artist" @Transactional {
                val uri = "http://localhost:${port}/concerts/data/artists"
                val artist = ArtistDto(
                    name = "Duran Duran",
                    gender = AGENDER,
                    careerStart = 1000L,
                    birthDate = LocalDateTime.now().toString(),
                    birthCity = "Birmingham",
                    country = "Great Britain",
                    keywords = "test"
                )
                val restTemplate = RestTemplate()
                val test: ResponseEntity<Unit> = restTemplate.postForEntity(uri, artist, Artist::class)

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
                artist.name shouldBe "Duran Duran"
                artist.gender shouldBe AGENDER
                artist.birthCity shouldBe "Birmingham"
            }
        }
    }

    override fun beforeEach(testCase: TestCase) {
        concertRepository.deleteAll()
        listingRepository.deleteAll()
        artistRepository.deleteAll()
        musicRepository.deleteAll()
        port  = environment.getProperty("local.server.port")?.toInt() ?: -1
        super.beforeEach(testCase)
    }


}


