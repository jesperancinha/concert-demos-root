package org.jesperancinha.concerts.mvc.controllers

import org.assertj.core.api.SoftAssertions
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.model.Music
import org.jesperancinha.concerts.mvc.repos.ArtistRepository
import org.jesperancinha.concerts.mvc.repos.ConcertRepository
import org.jesperancinha.concerts.mvc.repos.ListingRepository
import org.jesperancinha.concerts.mvc.repos.MusicRepository
import org.junit.jupiter.api.Disabled
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.time.LocalDateTime

import static TestConstants.HEY_MAMA
import static org.jesperancinha.concerts.types.Gender.FEMALE
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class ListingControllerImplITSpec extends Specification {

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


    def "GetAllListings"() {
        given: "An empty database"

        when: "Fetching all artists"
        final String uri = "http://localhost:${port}/concerts/data/listings"

        and: "Having a REST client"
        final RestTemplate restTemplate = new RestTemplate()

        and: "Making the REST Call"
        final List<Music> result = restTemplate.getForObject(uri, List.class)

        then: "Assert response of an empty array"
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(result).isEmpty()
        }
    }

    def "CreateListing"() {
        given: "An empty database"
        when:
        final String artistsUri = "http://localhost:${port}/concerts/data/artists"
        final String musicsUri = "http://localhost:${port}/concerts/data/musics"
        final String listingsUri = "http://localhost:${port}/concerts/data/listings"

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
        and:
        def listingDto = new ListingDto(
                savedArtistDto,
                savedMusicDto,
                List.of(savedMusicDto)
        )
        def savedListingDto = restTemplate.postForEntity(listingsUri, listingDto, ListingDto).body

        and:
        final ListingDto[] result = restTemplate.getForObject(listingsUri, ListingDto[].class)

        then:
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(result).isNotEmpty()
            softly.assertThat(result).hasSize(1)
            softly.assertThat(result[0].id).isNotEqualTo(0)
            softly.assertThat(result[0].id).isEqualTo(savedListingDto.id)
            softly.assertThat(result[0].artistDto).isEqualTo(artistDto)
            softly.assertThat(result[0].referenceMusicDto).isEqualTo(musicDto)
            softly.assertThat(result[0].musicDtos).hasSize(1)
            softly.assertThat(result[0].musicDtos.get(0)).isEqualTo(musicDto)
        }
    }

    def setup() {
        concertRepository.deleteAll()
        listingRepository.deleteAll()
        artistRepository.deleteAll()
        musicRepository.deleteAll()
    }
}
