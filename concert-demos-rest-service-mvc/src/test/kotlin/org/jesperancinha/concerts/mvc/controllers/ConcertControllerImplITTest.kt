package org.jesperancinha.concerts.mvc.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import org.assertj.core.api.Assertions.assertThat
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.HEY_MAMA
import org.jesperancinha.concerts.mvc.repos.ArtistRepository
import org.jesperancinha.concerts.mvc.repos.ConcertRepository
import org.jesperancinha.concerts.mvc.repos.ListingRepository
import org.jesperancinha.concerts.mvc.repos.MusicRepository
import org.jesperancinha.concerts.types.Gender.FEMALE
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.client.postForEntity
import java.net.URI
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class ConcertControllerImplITTest(
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
    fun `retrieve all concerts`() {
        val uri = "http://localhost:${port}/concerts/data/listings"
        val restTemplate = RestTemplate()
        val result: List<ConcertDto> = restTemplate.getForObject(uri, List::class)
        result.shouldBeEmpty()
    }

    @Test
    fun `create concerts`() {
        val artistsUri = "http://localhost:${port}/concerts/data/artists"
        val musicsUri = "http://localhost:${port}/concerts/data/musics"
        val listingsUri = "http://localhost:${port}/concerts/data/listings"
        val concertsUri = "http://localhost:${port}/concerts/data/concerts"

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
            0,
            savedArtistDto,
            savedMusicDto,
            mutableListOf(savedMusicDto)
        )
        val savedListingDto = restTemplate.postForEntity<ListingDto>(listingsUri, listingDto, ListingDto::class).body
        val concertDto = ConcertDto(
            name = "Nicki Wrld Tour",
            location = "Amsterdam",
            date = LocalDateTime.of(2019, 3, 25, 0, 0, 0).toString(),
            listingDtos = mutableListOf(savedListingDto)

        )
        val savedConcertDto = restTemplate.postForEntity<ConcertDto>(concertsUri, concertDto, ConcertDto::class).body
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

    @BeforeEach
    private fun setup() {
        concertRepository.deleteAll()
        listingRepository.deleteAll()
        artistRepository.deleteAll()
        musicRepository.deleteAll()
    }
}
