package org.jesperancinha.concerts.controllers

import org.assertj.core.api.SoftAssertions
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.model.Music
import org.jesperancinha.concerts.repos.ArtistRepository
import org.jesperancinha.concerts.repos.ConcertRepository
import org.jesperancinha.concerts.repos.ListingRepository
import org.jesperancinha.concerts.repos.MusicRepository
import org.jesperancinha.concerts.services.MusicService
import org.junit.jupiter.api.Disabled
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import static org.jesperancinha.concerts.controllers.TestConstants.HEY_MAMA
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@Disabled
class MusicControllerImplITSpec extends Specification {

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


    def "GetAllMusics"() {
        given: "An empty database"

        when: "Fetching all artists"
        final String uri = "http://localhost:${port}/concerts/data/musics"

        and: "Having a REST client"
        final RestTemplate restTemplate = new RestTemplate()

        and: "Making the REST Call"
        final List<Music> result = restTemplate.getForObject(uri, List.class)

        then: "Assert response of an empty array"
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(result).isEmpty()
        }
    }

    def "CreateMusic"() {
        given: "An empty database"

        when:
        final String uri = "http://localhost:${port}/concerts/data/musics"

        and:
        def musicDto = new MusicDto(
                "Hey mama",
                HEY_MAMA)

        and:
        final RestTemplate restTemplate = new RestTemplate()

        and:
        restTemplate.postForEntity(uri, musicDto, Music)

        and:
        final List<Music> result = restTemplate.getForObject(uri, List.class)

        then:
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(result).isNotEmpty()
            softly.assertThat(result).hasSize(1)
            softly.assertThat(result.get(0).id).isNotEqualTo(0)
            softly.assertThat(result.get(0).name).isEqualTo("Hey mama")
            softly.assertThat(result.get(0).lyrics).isEqualTo(HEY_MAMA)
        }
    }

    def setup() {
        concertRepository.deleteAll()
        listingRepository.deleteAll()
        artistRepository.deleteAll()
        musicRepository.deleteAll()
    }
}
