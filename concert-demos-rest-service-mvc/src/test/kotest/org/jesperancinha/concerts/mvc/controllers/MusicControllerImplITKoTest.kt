 package org.jesperancinha.concerts.mvc.controllers

import io.kotest.core.spec.style.WordSpec
import io.kotest.core.test.TestCase
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.HEY_MAMA
import org.jesperancinha.concerts.mvc.daos.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.core.ParameterizedTypeReference
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import java.net.URI
import kotlin.properties.Delegates

 @SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class MusicControllerImplITKoTest : WordSpec() {

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

    override fun extensions() = listOf(SpringExtension)

    final var port by Delegates.notNull<Int>()

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
                    name = "Hey mama",
                    lyrics = HEY_MAMA
                )
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

    override suspend fun beforeEach(testCase: TestCase) {
        super.beforeEach(testCase)
        withContext(Dispatchers.IO) {
            concertRepository.deleteAll()
            listingRepository.deleteAll()
            artistRepository.deleteAll()
            musicRepository.deleteAll()
        }
        port = environment.getProperty("local.server.port")?.toInt() ?: -1

    }
}
