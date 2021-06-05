package org.jesperancinha.concerts.controllers

import org.assertj.core.api.SoftAssertions
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.model.Concert
import org.jesperancinha.concerts.mvc.repos.ArtistRepository
import org.jesperancinha.concerts.mvc.repos.ConcertRepository
import org.jesperancinha.concerts.mvc.repos.ListingRepository
import org.jesperancinha.concerts.mvc.repos.MusicRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.time.LocalDateTime

import static org.jesperancinha.concerts.controllers.TestConstants.HEY_MAMA
import static org.jesperancinha.concerts.types.Gender.FEMALE
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class ConcertControllerImplITSpec extends Specification {

    @LocalServerPort
    private int port

    @Autowired
    private ListingRepository listingRepository

    @Autowired
    private ArtistRepository artistRepository

    @Autowired
    private MusicRepository musicRepository

    @Autowired
    private ConcertRepository concertRepository

    def "GetAllConcerts"() {
        given: "An empty database"

        when: "Fetching all artists"
        final String uri = "http://localhost:${port}/concerts/data/listings"

        and: "Having a REST client"
        final RestTemplate restTemplate = new RestTemplate()

        and: "Making the REST Call"
        final List<Concert> result = restTemplate.getForObject(uri, List.class)

        then: "Assert response of an empty array"
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(result).isEmpty()
        }
    }

    def "CreateConcert"() {
        given: "An empty database"
        when:
        final String artistsUri = "http://localhost:${port}/concerts/data/artists"
        final String musicsUri = "http://localhost:${port}/concerts/data/musics"
        final String listingsUri = "http://localhost:${port}/concerts/data/listings"
        final String concertsUri = "http://localhost:${port}/concerts/data/concerts"

        and:
        def musicDto = new MusicDto(
                "Hey mama",
                HEY_MAMA)
        def artistDto = new ArtistDto(
                "Nicky Minaj",
                FEMALE,
                1000L,
                LocalDateTime.now().toString(),
                "Port of Spain",
                "Trinidad en Tobago",
                "Rap")

        and:
        final RestTemplate restTemplate = new RestTemplate()

        and:
        def savedArtistDto = restTemplate.postForEntity(artistsUri, artistDto, ArtistDto.class).body
        def savedMusicDto = restTemplate.postForEntity(musicsUri, musicDto, MusicDto.class).body
        def listingDto = new ListingDto(
                0,
                savedArtistDto,
                savedMusicDto,
                List.of(savedMusicDto)
        )
        def savedListingDto = restTemplate.postForEntity(listingsUri, listingDto, ListingDto).body
        and:
        def concertDto = new ConcertDto(
                "Nicki Wrld Tour",
                "Amsterdam",
                LocalDateTime.of(2019, 3, 25, 0, 0, 0).toString(),
                List.of(savedListingDto)

        )
        def savedConcertDto = restTemplate.postForEntity(concertsUri, concertDto, ConcertDto).body
        and:
        def result = restTemplate.getForObject(concertsUri, ConcertDto[].class)

        then:
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(result).isNotEmpty()
            softly.assertThat(result).hasSize(1)
            final concertDtoResult = result[0]
            softly.assertThat(concertDtoResult.id).isNotEqualTo(0)
            softly.assertThat(concertDtoResult.id).isEqualTo(savedConcertDto.id)
            softly.assertThat(concertDtoResult.name).isEqualTo("Nicki Wrld Tour")
            softly.assertThat(concertDtoResult.location).isEqualTo("Amsterdam")
            softly.assertThat(concertDtoResult.listingDtos).hasSize(1)
            final listingDtoResult = concertDtoResult.listingDtos[0]
            softly.assertThat(listingDtoResult).isNotNull()
            softly.assertThat(listingDtoResult.artistDto).isEqualTo(savedArtistDto)
            softly.assertThat(listingDtoResult.referenceMusicDto).isEqualTo(savedMusicDto)
            softly.assertThat(listingDtoResult.musicDtos).hasSize(1)
            softly.assertThat(listingDtoResult.musicDtos[0]).isEqualTo(savedMusicDto)
        }
    }

    def setup() {
        concertRepository.deleteAll()
        listingRepository.deleteAll()
        artistRepository.deleteAll()
        musicRepository.deleteAll()
    }
}
