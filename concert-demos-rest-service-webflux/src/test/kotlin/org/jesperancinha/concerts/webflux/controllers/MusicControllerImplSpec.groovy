package org.jesperancinha.concerts.webflux.controllers

import org.assertj.core.api.SoftAssertions
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.webflux.configuration.ConfigurationProperties
import org.jesperancinha.concerts.webflux.model.Music
import org.jesperancinha.concerts.webflux.repos.*
import org.jesperancinha.concerts.webflux.services.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification

import java.util.function.Consumer

import static TestConstants.HEY_MAMA
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.when

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = [MusicControllerImpl, MusicController])
@ActiveProfiles("test")
@EnableConfigurationProperties(ConfigurationProperties)
@ComponentScan("org.jesperancinha.concerts.webflux.controllers")
class MusicControllerImplSpec extends Specification {

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
    private ArgumentCaptor<Music> argumentCaptor;

    def "GetAllMusics"() {
        when:
        when(musicService.getAllMusics()).thenReturn(Flux.just(new Music[0]))
        def target = '/concerts/data/musics'
        and:
        def results = webTestClient.get().uri(target)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()

        then:
        results.expectBody(MusicDto[]).value(new Consumer<MusicDto[]>() {
            @Override
            void accept(MusicDto[] responseMusicDto) {
                SoftAssertions.assertSoftly { softly ->
                    softly.assertThat(responseMusicDto).isEmpty()
                }
            }
        })
    }

    def "CreateMusic"() {
        when:
        def target = '/concerts/data/musics'

        and:
        def musicDto = new MusicDto(null,
                "Hey mama",
                HEY_MAMA)
        and:
        when(musicService.createMusic(any())).thenReturn(Mono.fromCallable({ -> musicDto }))
        and:
        def results = webTestClient.post().uri(target)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(musicDto), MusicDto.class)
                .exchange()
                .expectStatus().isOk()

        then:
        results.expectBody(MusicDto).value(new Consumer<MusicDto>() {
            @Override
            void accept(MusicDto responseMusicDto) {
                SoftAssertions.assertSoftly { softly ->
                    softly.assertThat(responseMusicDto).isEqualTo(musicDto)
                }
            }
        })
    }
}
