package org.jesperancinha.concerts.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.SoftAssertions
import org.jesperancinha.concerts.configuration.ConfigurationProperties
import org.jesperancinha.concerts.model.Artist
import org.jesperancinha.concerts.repos.ArtistRepository
import org.jesperancinha.concerts.services.ArtistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.time.LocalDateTime

import static org.jesperancinha.concerts.model.Gender.AGENDER
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@EnableConfigurationProperties(ConfigurationProperties)
@ActiveProfiles("test")
class ArtistControllerImplITSpec extends Specification {

    @LocalServerPort
    private int port;

    @Autowired
    private ArtistService artistService

    @Autowired
    private ArtistRepository artistRepository


    def "GetAllArtists"() {
        when:
        final String uri = "http://localhost:${port}/concerts/data/artists"
        and:
        final RestTemplate restTemplate = new RestTemplate();
        and:
        final List<Artist> result = restTemplate.getForObject(uri, List.class);
        then:
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(result).isEmpty()
        }
    }

//    def "CreateArtist"() {
//        when:
//        final String uri = "http://localhost:${port}/concerts/data/artists"
//        and:
//        def artist = new Artist(
//                "Duran Duran",
//                AGENDER,
//                1000L,
//                LocalDateTime.now().toString(),
//                "Birmingham",
//                "Great Britain",
//                "test")
//        and:
//        final RestTemplate restTemplate = new RestTemplate();
//        and:
//        restTemplate.postForEntity(uri, artist, Artist);
//        and:
//        final List<Artist> result = restTemplate.getForObject(uri, List.class)
//        then:
//        SoftAssertions.assertSoftly { softly ->
//            softly.assertThat(result).isNotEmpty()
//            softly.assertThat(result).hasSize(1)
//        }
//    }
}
