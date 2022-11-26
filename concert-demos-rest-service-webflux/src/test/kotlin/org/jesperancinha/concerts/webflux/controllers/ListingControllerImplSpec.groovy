package org.jesperancinha.concerts.webflux.controllers

import org.assertj.core.api.SoftAssertions
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.webflux.model.Listing
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

import static TestConstants.HEY_MAMA
import static org.jesperancinha.concerts.types.Gender.FEMALE
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.when

@WebFluxTest(controllers = [ListingControllerImpl, ListingController])
@ActiveProfiles("test")
class ListingControllerImplSpec extends Specification {

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
    private ArgumentCaptor<Listing> argumentCaptor

    def "GetAllListings"() {
        when:
        when(listingService.getAllListings()).thenReturn(Flux.just(new Listing[0]))
        def target = '/concerts/data/listings'

        and:
        def results = webTestClient.get().uri(target)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
        then:
        results.expectBody(ListingDto[]).value(new Consumer<ListingDto[]>() {
            @Override
            void accept(ListingDto[] responseListingDto) {
                SoftAssertions.assertSoftly { softly ->
                    softly.assertThat(responseListingDto).isEmpty()
                }
            }
        })
    }

    def "CreateListing"() {
        when:
        def target = '/concerts/data/listings'

        and:
        def musicDto = new MusicDto(
                1L,
                "Hey mama",
                HEY_MAMA)
        def artistDto = new ArtistDto(null,
                "Nicky Minaj",
                FEMALE,
                1000L,
                LocalDateTime.now().toString(),
                "Port of Spain",
                "Trinidad en Tobago",
                "Rap")
        def listingDto = new ListingDto(
                1L,
                artistDto,
                musicDto,
                List.of(musicDto)
        )

        and:
        when(listingService.createListing(any())).thenReturn(Mono.fromCallable({ -> listingDto }))
        and:
        def results = webTestClient.post().uri(target)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(listingDto), ListingDto.class)
                .exchange()
                .expectStatus().isOk()

        then:
        results.expectBody(ListingDto).value(new Consumer<ListingDto>() {
            @Override
            void accept(ListingDto responseListingDto) {
                SoftAssertions.assertSoftly { softly ->
                    softly.assertThat(responseListingDto.id).isNotNull()
                    softly.assertThat(responseListingDto.artistDto).isEqualTo(artistDto)
                    softly.assertThat(responseListingDto.referenceMusicDto).isEqualTo(musicDto)
                    softly.assertThat(responseListingDto.musicDtos).hasSize(1)
                    softly.assertThat(responseListingDto.musicDtos.get(0)).isEqualTo(musicDto)
                }
            }
        })
    }
}