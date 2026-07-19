package org.jesperancinha.concerts.webflux.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.webflux.configuration.ConfigurationProperties
import org.jesperancinha.concerts.webflux.controllers.TestConstants.Companion.LYRICS_TEXT
import org.jesperancinha.concerts.webflux.model.Music
import org.jesperancinha.concerts.webflux.repos.*
import org.jesperancinha.concerts.webflux.services.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@WebFluxTest(controllers = [MusicControllerImpl::class, MusicController::class])
@ActiveProfiles("test")
@EnableConfigurationProperties(ConfigurationProperties::class)
@ComponentScan("org.jesperancinha.concerts.webflux.controllers")
class MusicControllerImplSpec @Autowired constructor(
    private val webTestClient: WebTestClient
) {

    @MockitoBean
    lateinit var musicService: MusicService

    @MockitoBean
    lateinit var musicRepository: MusicRepository

    @MockitoBean
    lateinit var artistService: ArtistService

    @MockitoBean
    lateinit var artistRepository: ArtistRepository

    @MockitoBean
    lateinit var concertService: ConcertService

    @MockitoBean
    lateinit var concertRepository: ConcertRepository

    @MockitoBean
    lateinit var concertListingService: ConcertListingService

    @MockitoBean
    lateinit var concertListingRepository: ConcertListingRepository

    @MockitoBean
    lateinit var listingService: ListingService

    @MockitoBean
    lateinit var repository: ListingRepository

    @MockitoBean
    lateinit var listingMusicService: ListingMusicService

    @MockitoBean
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
        results.expectBody<List<*>>().value { list -> list.shouldBeEmpty() }
    }

    @Test
    fun `should CreateMusic`() {
        val target = "/concerts/data/musics"
        val musicDto = MusicDto(
            null,
            "Hey mama",
            LYRICS_TEXT
        )
        `when`(musicService.createMusic(musicDto)).thenReturn(Mono.fromCallable { musicDto })
        val results = webTestClient.post().uri(target)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(musicDto), MusicDto::class.java)
            .exchange()
            .expectStatus().isOk
        results.expectBody<MusicDto>().value { responseMusicDto -> responseMusicDto shouldBe musicDto }
    }
}
