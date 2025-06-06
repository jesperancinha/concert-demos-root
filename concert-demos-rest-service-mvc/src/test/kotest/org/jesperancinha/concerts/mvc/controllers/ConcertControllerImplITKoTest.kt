package org.jesperancinha.concerts.mvc.controllers

import io.kotest.core.spec.style.WordSpec
import io.kotest.core.test.TestCase
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.assertj.core.api.Assertions.assertThat
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.FORREST_PLACE
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.LYRICS_TEXT
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.SPARROW
import org.jesperancinha.concerts.mvc.daos.ArtistRepository
import org.jesperancinha.concerts.mvc.daos.ConcertRepository
import org.jesperancinha.concerts.mvc.daos.ListingRepository
import org.jesperancinha.concerts.mvc.daos.MusicRepository
import org.jesperancinha.concerts.types.Gender.FEMALE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.core.ParameterizedTypeReference
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.client.postForEntity
import java.net.URI
import java.time.LocalDateTime
import kotlin.properties.Delegates

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class ConcertControllerImplITKoTest : WordSpec() {

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
        "concert controller" should {
            "retrieve all concerts" {
                val uri = "http://localhost:${port}/concerts/data/listings"
                val restTemplate = RestTemplate()
                val result: List<ConcertDto> = restTemplate.getForObject(uri, List::class)
                result.shouldBeEmpty()
            }
            "create concerts" {
                val artistsUri = "http://localhost:${port}/concerts/data/artists"
                val musicsUri = "http://localhost:${port}/concerts/data/musics"
                val listingsUri = "http://localhost:${port}/concerts/data/listings"
                val concertsUri = "http://localhost:${port}/concerts/data/concerts"

                val musicDto = MusicDto(
                    name = "Hey mama",
                    lyrics = LYRICS_TEXT
                )
                val artistDto = ArtistDto(
                    name = SPARROW,
                    gender = FEMALE,
                    careerStart = 1000L,
                    birthDate = LocalDateTime.now().toString(),
                    birthCity = FORREST_PLACE,
                    country = "The Kingdom Land",
                    keywords = "Rap"
                )

                val restTemplate = RestTemplate()


                val savedArtistDto = restTemplate.postForEntity<ArtistDto>(artistsUri, artistDto, ArtistDto::class).body
                val savedMusicDto = restTemplate.postForEntity<MusicDto>(musicsUri, musicDto, MusicDto::class).body

                savedArtistDto.shouldNotBeNull()
                savedMusicDto.shouldNotBeNull()

                val listingDto = ListingDto(
                    artistDto = savedArtistDto,
                    referenceMusicDto = savedMusicDto,
                    musicDtos = mutableListOf(savedMusicDto)
                )
                val savedListingDto =
                    restTemplate.postForEntity<ListingDto>(listingsUri, listingDto, ListingDto::class).body
                val concertDto = ConcertDto(
                    name = "Nicki Wrld Tour",
                    location = "Amsterdam",
                    date = LocalDateTime.of(2019, 3, 25, 0, 0, 0).toString(),
                    listingDtos = mutableListOf(savedListingDto)
                )
                val savedConcertDto =
                    restTemplate.postForEntity<ConcertDto>(concertsUri, concertDto, ConcertDto::class).body
                val request = RequestEntity<Any>(HttpMethod.GET, URI.create(concertsUri))
                val respType = object : ParameterizedTypeReference<List<ConcertDto>>() {}
                val response = restTemplate.exchange(request, respType)
                val result: List<ConcertDto> = response.body ?: listOf()

                assertThat(result).isNotEmpty
                assertThat(result).hasSize(1)
                val concertDtoResult = result.getOrNull(0)
                assertThat(concertDtoResult?.id).isNotEqualTo(0)
                assertThat(concertDtoResult?.id).isEqualTo(savedConcertDto?.id)
                assertThat(concertDtoResult?.name).isEqualTo("Nicki Wrld Tour")
                assertThat(concertDtoResult?.location).isEqualTo("Amsterdam")
                assertThat(concertDtoResult?.listingDtos).hasSize(1)
                val listingDtoResult = concertDtoResult?.listingDtos?.getOrNull(0)
                assertThat(listingDtoResult).isNotNull
                assertThat(listingDtoResult?.artistDto).isEqualTo(savedArtistDto)
                assertThat(listingDtoResult?.referenceMusicDto).isEqualTo(savedMusicDto)
                assertThat(listingDtoResult?.musicDtos).hasSize(1)
                assertThat(listingDtoResult?.musicDtos?.getOrNull(0)).isEqualTo(savedMusicDto)
            }
        }
    }

    override suspend fun beforeEach(testCase: TestCase) {
        withContext(Dispatchers.IO) {
            concertRepository.deleteAll()
            listingRepository.deleteAll()
            artistRepository.deleteAll()
            musicRepository.deleteAll()
        }
        port = environment.getProperty("local.server.port")?.toInt() ?: -1
        super.beforeEach(testCase)
    }
}


