package org.jesperancinha.concerts.controllers


import org.jesperancinha.concerts.repos.ArtistRepository
import org.jesperancinha.concerts.services.ArtistService
import org.junit.jupiter.api.Disabled
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@Disabled
class ArtistControllerImplITSpec extends Specification {

    @LocalServerPort
    private int port

    @Autowired
    private ArtistService artistService

    @Autowired
    private ArtistRepository artistRepository


    @Disabled
    def "GetAllArtists"() {
//        given: "An empty database"
//
//        when: "Fetching all artists"
//        final String uri = "http://localhost:${port}/concerts/data/artists"
//
//        and: "Having a REST client"
//        final RestTemplate restTemplate = new RestTemplate()
//
//        and: "Making the REST Call"
//        final List<Artist> result = restTemplate.getForObject(uri, List.class)
//
//        then: "Assert response of an empty array"
//        SoftAssertions.assertSoftly { softly ->
//            softly.assertThat(result).isEmpty()
//        }
    }

    @Disabled
    def "CreateArtist"() {
//        given: "An empty database"
//
//        when:
//        final String uri = "http://localhost:${port}/concerts/data/artists"
//
//        and:
//        def artist = new ArtistDto(
//                "Duran Duran",
//                AGENDER,
//                1000L,
//                LocalDateTime.now().toString(),
//                "Birmingham",
//                "Great Britain",
//                "test")
//
//        and:
//        final RestTemplate restTemplate = new RestTemplate()
//
//        and:
//        restTemplate.postForEntity(uri, artist, Artist)
//
//        and:
//        final List<Artist> result = restTemplate.getForObject(uri, List.class)
//
//        then:
//        SoftAssertions.assertSoftly { softly ->
//            softly.assertThat(result).isNotEmpty()
//            softly.assertThat(result).hasSize(1)
//            softly.assertThat(result.get(0).id).isNotEqualTo(0)
//            softly.assertThat(result.get(0).name).isEqualTo("Duran Duran")
//            softly.assertThat(result.get(0).gender as Gender).isSameAs(AGENDER)
//            softly.assertThat(result.get(0).birthCity).isEqualTo("Birmingham")
//        }
    }

    def setup() {
        artistRepository.deleteAll()
    }
}
