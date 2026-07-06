package org.jesperancinha.concerts.webflux.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.types.Gender
import org.jesperancinha.concerts.webflux.model.Artist
import org.jesperancinha.concerts.webflux.repos.*
import org.jesperancinha.concerts.webflux.services.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@WebFluxTest(controllers = [ArtistControllerImpl::class, ArtistController::class])
@ActiveProfiles("test")
class ArtistControllerImplSpec @Autowired constructor(
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
    lateinit var argumentCaptor: ArgumentCaptor<Artist>

    @Test
    fun `should GetAllArtists`() {
        Mockito.`when`(artistService.getAllArtists()).thenReturn(Flux.just())
        val target = "/concerts/data/artists"
        val results = webTestClient.get().uri(target)
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
        results.expectBody<List<*>>().value { list -> list.shouldBeEmpty() }
    }

    @Test
    fun `should CreateArtist`() {
        val target = "/concerts/data/artists"

        val artistDto = ArtistDto(
            null,
            "Duran Duran",
            Gender.NONAPPLICABLE,
            1000L,
            LocalDateTime.now().toString(),
            "Birmingham",
            "Great Britain",
            "test"
        )
        Mockito.`when`(artistService.createArtist(artistDto)).thenReturn(Mono.just(artistDto))
        val results = webTestClient.post().uri(target)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .body(
                Mono.just(artistDto), ArtistDto::class.java
            )
            .exchange()
            .expectStatus().isOk
        results.expectBody<ArtistDto>().value { artistResponse ->
            artistResponse shouldBe artistDto
        }
    }
}
