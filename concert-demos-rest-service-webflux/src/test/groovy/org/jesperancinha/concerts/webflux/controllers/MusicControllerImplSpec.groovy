package org.jesperancinha.concerts.webflux.controllers

import org.assertj.core.api.SoftAssertions
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.webflux.model.Music
import org.jesperancinha.concerts.webflux.services.MusicService
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification

import java.util.function.Consumer

import static TestConstants.HEY_MAMA
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.when

@WebFluxTest(controllers = [MusicControllerImpl, MusicController])
class MusicControllerImplSpec extends Specification {

    @Autowired
    private WebTestClient webTestClient

    @MockBean
    private MusicService musicService

    @MockBean
    private DatabaseClient databaseClient

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
        def musicDto = new MusicDto(
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
