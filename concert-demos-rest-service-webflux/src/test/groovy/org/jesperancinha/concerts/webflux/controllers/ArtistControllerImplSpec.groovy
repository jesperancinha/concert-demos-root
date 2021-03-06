package org.jesperancinha.concerts.webflux.controllers

import org.assertj.core.api.SoftAssertions
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.webflux.controllers.ArtistController
import org.jesperancinha.concerts.webflux.controllers.ArtistControllerImpl
import org.jesperancinha.concerts.webflux.model.Artist
import org.jesperancinha.concerts.webflux.repos.*
import org.jesperancinha.concerts.webflux.services.*
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification

import java.time.LocalDateTime
import java.util.function.Consumer

import static org.jesperancinha.concerts.types.Gender.AGENDER
import static org.mockito.Mockito.when

@WebFluxTest(controllers = [ArtistControllerImpl, ArtistController])
@ActiveProfiles("test")
class ArtistControllerImplSpec extends Specification {

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
    private ListingRepository repository;
    @MockBean
    private ListingMusicService listingMusicService;
    @MockBean
    private ListingMusicRepository listingMusicRepository

    @Captor
    private ArgumentCaptor<Artist> argumentCaptor

    def "GetAllArtists"() {
        when:
        when(artistService.getAllArtists()).thenReturn(Flux.just(new Artist[0]))
        def target = '/concerts/data/artists'
        and:
        def results = webTestClient.get().uri(target)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
        then:
        results.expectBody(ArtistDto[]).value(new Consumer<ArtistDto[]>() {
            @Override
            void accept(ArtistDto[] responseArtisDto) {
                SoftAssertions.assertSoftly { softly ->
                    softly.assertThat(responseArtisDto).isEmpty()
                }
            }
        })
    }

    def "CreateArtist"() {
        when:
        def target = '/concerts/data/artists'

        and:
        def artistDto = new ArtistDto(
                "Duran Duran",
                AGENDER,
                1000L,
                LocalDateTime.now().toString(),
                "Birmingham",
                "Great Britain",
                "test")
        and:
        def results = webTestClient.post().uri(target)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(artistDto), ArtistDto.class)
                .exchange()
                .expectStatus().isOk()

        then:
        results.expectBody(ArtistDto).value(new Consumer<ArtistDto>() {
            @Override
            void accept(ArtistDto responseArtisDto) {
                SoftAssertions.assertSoftly { softly ->
                    softly.assertThat(responseArtisDto).isEqualTo(responseArtisDto)
                }
            }
        })
    }
}
