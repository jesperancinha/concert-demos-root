package org.jesperancinha.concerts.mvc.controllers

import io.kotest.core.spec.style.WordSpec
import io.kotest.core.test.TestCase
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.HEY_MAMA
import org.jesperancinha.concerts.mvc.model.Music
import org.jesperancinha.concerts.mvc.repos.ArtistRepository
import org.jesperancinha.concerts.mvc.repos.ConcertRepository
import org.jesperancinha.concerts.mvc.repos.ListingRepository
import org.jesperancinha.concerts.mvc.repos.MusicRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import java.net.URI

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class MusicControllerImplITKoTest(
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
) : WordSpec() {

    override fun extensions() = listOf(SpringExtension)

    init {
        "Spring Extension" should {
            "retrieve all music" {
                val uri = "http://localhost:${port}/concerts/data/musics"
                val restTemplate = RestTemplate()
                val request = RequestEntity<Any>(HttpMethod.GET, URI.create(uri))
                val respType = object : ParameterizedTypeReference<List<MusicDto>>() {}
                val response = restTemplate.exchange(request, respType)
                val result: List<MusicDto> = response.body ?: listOf()
                result.shouldBeEmpty()
            }

            "create music" {
                val uri = "http://localhost:${port}/concerts/data/musics"
                val musicDto = MusicDto(
                    name="Hey mama",
                   lyrics= HEY_MAMA)
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
                musicDto1.lyrics shouldBe HEY_MAMA
            }

        }
    }

    override fun beforeEach(testCase: TestCase) {
        super.beforeEach(testCase)
        concertRepository.deleteAll()
        listingRepository.deleteAll()
        artistRepository.deleteAll()
        musicRepository.deleteAll()
    }
}
