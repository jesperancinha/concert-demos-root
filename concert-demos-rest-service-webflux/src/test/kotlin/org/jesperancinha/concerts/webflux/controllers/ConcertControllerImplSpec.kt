package org.jesperancinha.concerts.webflux.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.types.Gender.FEMALE
import org.jesperancinha.concerts.webflux.controllers.TestConstants.Companion.FORREST_PLACE
import org.jesperancinha.concerts.webflux.controllers.TestConstants.Companion.LYRICS_TEXT
import org.jesperancinha.concerts.webflux.controllers.TestConstants.Companion.SPARROW
import org.jesperancinha.concerts.webflux.model.Concert
import org.jesperancinha.concerts.webflux.repos.*
import org.jesperancinha.concerts.webflux.services.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@WebFluxTest(controllers = [ConcertControllerImpl::class, ConcertController::class])
@ActiveProfiles("test")
class ConcertControllerImplSpec @Autowired constructor(
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
    lateinit var argumentCaptor: ArgumentCaptor<Concert>

    @Test
    fun `should GetAllConcerts`() {
        `when`(concertService.getAllConcerts()).thenReturn(Flux.just())
        val target = "/concerts/data/concerts"
        val results = webTestClient.get().uri(target)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
        results.expectBody<List<*>>().value { list -> list.shouldBeEmpty() }
    }

    @Test
    fun `should CreateConcert`() {
        val target = "/concerts/data/concerts"
        val musicDto = MusicDto(
            1,
            "Hey mama",
            LYRICS_TEXT
        )
        val artistDto = ArtistDto(
            1,
            SPARROW,
            FEMALE,
            1000L,
            LocalDateTime.now().toString(),
            FORREST_PLACE,
            "The Kingdom Land",
            "Rap"
        )
        val listingDto = ListingDto(
            1,
            artistDto,
            musicDto,
            mutableListOf(musicDto)
        )
        val concertDto = ConcertDto(
            null,
            "Nicki Wrld Tour",
            "Amsterdam",
            LocalDateTime.of(2019, 3, 25, 0, 0, 0).toString(),
            mutableListOf(listingDto)
        )

        `when`(concertService.createConcert(concertDto)).thenReturn(Mono.just(concertDto))
        val results = webTestClient.post().uri(target)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(concertDto), ConcertDto::class.java)
            .exchange()
            .expectStatus().isOk

        results.expectBody<ConcertDto>().value { concert ->
            concert shouldBe concertDto
        }
    }
}
