package org.jesperancinha.concerts.controllers

import org.assertj.core.api.SoftAssertions
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.model.Artist
import org.jesperancinha.concerts.repos.ArtistRepository
import org.jesperancinha.concerts.services.ArtistService
import org.jesperancinha.concerts.types.Gender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.time.LocalDateTime

import static org.jesperancinha.concerts.types.Gender.AGENDER
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class ArtistControllerImplITSpec extends Specification {

    @LocalServerPort
    private int port

    @Autowired
    private ArtistService artistService

    @Autowired
    private ArtistRepository artistRepository


    def "GetAllArtists"() {
        given: "An empty database"

        when: "Fetching all artists"
        final String uri = "http://localhost:${port}/concerts/data/artists"

        and: "Having a REST client"
        final RestTemplate restTemplate = new RestTemplate()

        and: "Making the REST Call"
        final List<Artist> result = restTemplate.getForObject(uri, List.class)

        then: "Assert response of an empty array"
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(result).isEmpty()
        }
    }

    def "CreateArtist"() {
        given: "An empty database"

        when:
        final String uri = "http://localhost:${port}/concerts/data/artists"

        and:
        def artist = new ArtistDto(
                "Duran Duran",
                AGENDER,
                1000L,
                LocalDateTime.now().toString(),
                "Birmingham",
                "Great Britain",
                "test")

        and:
        final RestTemplate restTemplate = new RestTemplate()

        and:
        restTemplate.postForEntity(uri, artist, Artist)

        and:
        final List<Artist> result = restTemplate.getForObject(uri, List.class)

        then:
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(result).isNotEmpty()
            softly.assertThat(result).hasSize(1)
            softly.assertThat(result.get(0).id).isNotEqualTo(0)
            softly.assertThat(result.get(0).name).isEqualTo("Duran Duran")
            softly.assertThat(result.get(0).gender as Gender).isSameAs(AGENDER)
            softly.assertThat(result.get(0).birthCity).isEqualTo("Birmingham")
        }
    }

    def setup() {
        artistRepository.deleteAll().block()
    }
}
