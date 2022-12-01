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
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@WebFluxTest(controllers = [ArtistControllerImpl::class, ArtistController::class])
@ActiveProfiles("test")
class ArtistControllerImplSpec {

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
    lateinit var argumentCaptor: ArgumentCaptor<Artist>

    @Test
    fun `should GetAllArtists`() {
        Mockito.`when`(artistService.getAllArtists()).thenReturn(Flux.just())
        val target = "/concerts/data/artists"
        val results = webTestClient.get().uri(target)
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
        results.expectBody(List::class.java).value { list -> list.shouldBeEmpty() }
    }

    @Test
    fun `should CreateArtist`() {
        val target = "/concerts/data/artists"

        val artistDto = ArtistDto(
            null,
            "Duran Duran",
            Gender.AGENDER,
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
        results.expectBody(ArtistDto::class.java).value { artistResponse ->
            artistResponse shouldBe artistDto
        }
    }
}
