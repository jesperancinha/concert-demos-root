package org.jesperancinha.concerts.webflux.controllers

import org.assertj.core.api.SoftAssertions
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.webflux.model.Concert
import org.jesperancinha.concerts.webflux.repos.ArtistRepository
import org.jesperancinha.concerts.webflux.repos.ConcertListingRepository
import org.jesperancinha.concerts.webflux.repos.ConcertRepository
import org.jesperancinha.concerts.webflux.repos.ListingMusicRepository
import org.jesperancinha.concerts.webflux.repos.ListingRepository
import org.jesperancinha.concerts.webflux.repos.MusicRepository
import org.jesperancinha.concerts.webflux.services.ArtistService
import org.jesperancinha.concerts.webflux.services.ConcertListingService
import org.jesperancinha.concerts.webflux.services.ConcertService
import org.jesperancinha.concerts.webflux.services.ListingMusicService
import org.jesperancinha.concerts.webflux.services.ListingService
import org.jesperancinha.concerts.webflux.services.MusicService
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.http.MediaType
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification

import java.time.LocalDateTime
import java.util.function.Consumer

import static TestConstants.HEY_MAMA
import static org.jesperancinha.concerts.types.Gender.FEMALE
import static org.mockito.Mockito.when

@WebFluxTest(controllers = [ConcertControllerImpl, ConcertController])
@ActiveProfiles("test")
class ConcertControllerImplSpec extends Specification {

    @Autowired
    private WebTestClient webTestClient

    @MockBean
    private MusicService musicService
    @MockBean
    private MusicRepository musicRepository;
    @MockBean
    private ArtistService artistService
    @MockBean
    private ArtistRepository artistRepository;
    @MockBean
    private ConcertService concertService
    @MockBean
    private ConcertRepository concertRepository
    @MockBean
    private ConcertListingService concertListingService
    @MockBean
    private ConcertListingRepository concertListingRepository
    @MockBean
    private ListingService listingService
    @MockBean
    private ListingRepository repository
    @MockBean
    private ListingMusicService listingMusicService
    @MockBean
    private ListingMusicRepository listingMusicRepository

    @Captor
    private ArgumentCaptor<Concert> argumentCaptor

    def "GetAllConcerts"() {
        when:
        when(concertService.getAllConcerts()).thenReturn(Flux.just(new Concert[0]))
        def target = '/concerts/data/concerts'

        and:
        def results = webTestClient.get().uri(target)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
        then:
        results.expectBody(ConcertDto[]).value(new Consumer<ConcertDto[]>() {
            @Override
            void accept(ConcertDto[] responseConcertDto) {
                SoftAssertions.assertSoftly { softly ->
                    softly.assertThat(responseConcertDto).isEmpty()
                }
            }
        })
    }

    def "CreateConcert"() {
        when:
        def target = '/concerts/data/concerts'

        and:
        def musicDto = new MusicDto(
                1,
                "Hey mama",
                HEY_MAMA)
        def artistDto = new ArtistDto(
                1,
                "Nicky Minaj",
                FEMALE,
                1000L,
                LocalDateTime.now().toString(),
                "Port of Spain",
                "Trinidad en Tobago",
                "Rap")
        def listingDto = new ListingDto(
                1,
                artistDto,
                musicDto,
                List.of(musicDto)
        )
        def concertDto = new ConcertDto(
                null,
                "Nicki Wrld Tour",
                "Amsterdam",
                LocalDateTime.of(2019, 3, 25, 0, 0, 0).toString(),
                List.of(listingDto)
        )

        and:
        when(concertService.createConcert(concertDto)).thenReturn(Mono.just(concertDto))
        and:
        def results = webTestClient.post().uri(target)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(concertDto), ConcertDto.class)
                .exchange()
                .expectStatus().isOk()

        then:
        results.expectBody(ConcertDto).value(new Consumer<ConcertDto>() {
            @Override
            void accept(ConcertDto responseConcertDto) {
                SoftAssertions.assertSoftly { softly ->
                    softly.assertThat(responseConcertDto).isEqualTo(concertDto)
                }
            }
        })
    }
}
