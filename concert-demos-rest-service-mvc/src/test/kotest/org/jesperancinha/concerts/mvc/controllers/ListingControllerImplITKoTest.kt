package org.jesperancinha.concerts.mvc.controllers

import io.kotest.core.spec.style.WordSpec
import io.kotest.core.test.TestCase
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.HEY_MAMA
import org.jesperancinha.concerts.mvc.model.Music
import org.jesperancinha.concerts.mvc.repos.ArtistRepository
import org.jesperancinha.concerts.mvc.repos.ConcertRepository
import org.jesperancinha.concerts.mvc.repos.ListingRepository
import org.jesperancinha.concerts.mvc.repos.MusicRepository
import org.jesperancinha.concerts.types.Gender.FEMALE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity
import java.net.URI
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class ListingControllerImplITKoTest(
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
            "retrieve all listings" {
                val uri = "http://localhost:${port}/concerts/data/listings"
                val restTemplate = RestTemplate()
                val request = RequestEntity<Any>(HttpMethod.GET, URI.create(uri))
                val respType = object : ParameterizedTypeReference<List<Music>>() {}
                val response = restTemplate.exchange(request, respType)
                val result: List<Music> = response.body ?: listOf()
                result.shouldBeEmpty()
            }
            "create listings"{
                val artistsUri = "http://localhost:${port}/concerts/data/artists"
                val musicsUri = "http://localhost:${port}/concerts/data/musics"
                val listingsUri = "http://localhost:${port}/concerts/data/listings"
                val musicDto = MusicDto(
                    name = "Hey mama",
                    lyrics = HEY_MAMA
                )
                val artistDto = ArtistDto(
                    name = "Nicky Minaj",
                    gender = FEMALE,
                    careerStart = 1000L,
                    birthDate = LocalDateTime.now().toString(),
                    birthCity = "Port of Spain",
                    country = "Trinidad en Tobago",
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

                val request = RequestEntity<Any>(HttpMethod.GET, URI.create(listingsUri))
                val respType = object : ParameterizedTypeReference<List<ListingDto>>() {}
                val response = restTemplate.exchange(request, respType)
                val result: List<ListingDto> = response.body ?: listOf()

                result.shouldNotBeEmpty()
                result.shouldHaveSize(1)
                val listingDto1 = result[0]
                listingDto1 shouldNotBe 0
                listingDto1.id shouldBe savedListingDto?.id
                listingDto1.artistDto shouldBe artistDto
                listingDto1.referenceMusicDto shouldBe musicDto
                listingDto1.musicDtos.shouldHaveSize(1)
                listingDto1.musicDtos.get(0).shouldBe(musicDto)
            }
        }

    }

    override fun beforeEach(testCase: TestCase) {
        concertRepository.deleteAll()
        listingRepository.deleteAll()
        artistRepository.deleteAll()
        musicRepository.deleteAll()
        super.beforeEach(testCase)
    }
}
