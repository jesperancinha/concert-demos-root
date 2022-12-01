package org.jesperancinha.concerts.webflux.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.webflux.configuration.ConfigurationProperties
import org.jesperancinha.concerts.webflux.controllers.TestConstants.Companion.HEY_MAMA
import org.jesperancinha.concerts.webflux.model.Music
import org.jesperancinha.concerts.webflux.repos.*
import org.jesperancinha.concerts.webflux.services.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@WebFluxTest(controllers = [MusicControllerImpl::class, MusicController::class])
@ActiveProfiles("test")
@EnableConfigurationProperties(ConfigurationProperties::class)
@ComponentScan("org.jesperancinha.concerts.webflux.controllers")
class MusicControllerImplSpec {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var musicService: MusicService

    @MockBean
    lateinit var musicRepository: MusicRepository

    @MockBean
    lateinit var artistService: ArtistService

    @MockBean
    lateinit var artistRepository: ArtistRepository

    @MockBean
    lateinit var concertService: ConcertService

    @MockBean
    lateinit var concertRepository: ConcertRepository

    @MockBean
    lateinit var concertListingService: ConcertListingService

    @MockBean
    lateinit var concertListingRepository: ConcertListingRepository

    @MockBean
    lateinit var listingService: ListingService

    @MockBean
    lateinit var repository: ListingRepository

    @MockBean
    lateinit var listingMusicService: ListingMusicService

    @MockBean
    lateinit var listingMusicRepository: ListingMusicRepository

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Music>

    @Test
    fun `should GetAllMusics`() {
        `when`(musicService.getAllMusics()).thenReturn(Flux.just())
        val target = "/concerts/data/musics"
        val results = webTestClient.get().uri(target)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
        results.expectBody(List::class.java).value { list -> list.shouldBeEmpty() }
    }

    @Test
    fun `should CreateMusic`() {
        val target = "/concerts/data/musics"
        val musicDto = MusicDto(
            null,
            "Hey mama",
            HEY_MAMA
        )
        `when`(musicService.createMusic(musicDto)).thenReturn(Mono.fromCallable { musicDto })
        val results = webTestClient.post().uri(target)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(musicDto), MusicDto::class.java)
            .exchange()
            .expectStatus().isOk
        results.expectBody(MusicDto::class.java).value { responseMusicDto -> responseMusicDto shouldBe musicDto }
    }
}
