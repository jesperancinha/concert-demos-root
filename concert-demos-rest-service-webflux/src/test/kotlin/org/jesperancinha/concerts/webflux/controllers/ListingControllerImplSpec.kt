package org.jesperancinha.concerts.webflux.controllers

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.types.Gender
import org.jesperancinha.concerts.webflux.controllers.TestConstants.Companion.FORREST_PLACE
import org.jesperancinha.concerts.webflux.controllers.TestConstants.Companion.LYRICS_TEXT
import org.jesperancinha.concerts.webflux.controllers.TestConstants.Companion.SPARROW
import org.jesperancinha.concerts.webflux.model.Listing
import org.jesperancinha.concerts.webflux.repos.*
import org.jesperancinha.concerts.webflux.services.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@WebFluxTest(controllers = [ListingControllerImpl::class, ListingController::class])
@ActiveProfiles("test")
class ListingControllerImplSpec @Autowired constructor(
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
    lateinit var argumentCaptor: ArgumentCaptor<Listing>

    @Test
    fun `should GetAllListings`() {
        `when`(listingService.getAllListings()).thenReturn(Flux.just())
        val target = "/concerts/data/listings"
        val results = webTestClient.get().uri(target)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
        results.expectBody<List<*>>().value { list -> list.shouldBeEmpty() }
    }

    @Test
    fun `should CreateListing`() {
        val target = "/concerts/data/listings"
        val musicDto = MusicDto(
            1L,
            "Hey mama",
            LYRICS_TEXT
        )
        val artistDto = ArtistDto(
            name = SPARROW,
            gender = Gender.FEMALE,
            careerStart = 1000L,
            birthDate = LocalDateTime.now().toString(),
            birthCity = FORREST_PLACE,
            country = "The Kingdom Land",
            keywords = "Rap"
        )
        val listingDto = ListingDto(
            id = 1L,
            artistDto = artistDto,
            referenceMusicDto = musicDto,
            musicDtos = mutableListOf(musicDto)
        )

        `when`(listingService.createListing(listingDto)).thenReturn(Mono.fromCallable { listingDto })
        val results = webTestClient.post().uri(target)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(listingDto), ListingDto::class.java)
            .exchange()
            .expectStatus().isOk
        results.expectBody<ListingDto>().value { responseListingDto ->
            responseListingDto.shouldNotBeNull().id.shouldNotBeNull()
            responseListingDto.artistDto shouldBe artistDto
            responseListingDto.referenceMusicDto shouldBe musicDto
            responseListingDto.musicDtos.shouldHaveSize(1)
            responseListingDto.musicDtos[0] shouldBe musicDto
        }
    }
}