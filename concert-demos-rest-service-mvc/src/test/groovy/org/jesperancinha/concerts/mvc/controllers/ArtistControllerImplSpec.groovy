package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.mvc.controllers.ArtistController
import org.jesperancinha.concerts.mvc.controllers.ArtistControllerImpl
import org.jesperancinha.concerts.mvc.model.Artist
import org.jesperancinha.concerts.mvc.repos.ArtistRepository
import org.jesperancinha.concerts.mvc.repos.ConcertRepository
import org.jesperancinha.concerts.mvc.repos.ListingRepository
import org.jesperancinha.concerts.mvc.repos.MusicRepository
import org.jesperancinha.concerts.mvc.services.ArtistService
import org.jesperancinha.concerts.mvc.services.ConcertService
import org.jesperancinha.concerts.mvc.services.ListingService
import org.jesperancinha.concerts.mvc.services.MusicService
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.time.LocalDateTime

import static org.jesperancinha.concerts.types.Gender.AGENDER
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [ArtistControllerImpl, ArtistController])
class ArtistControllerImplSpec extends Specification {

    @Autowired
    private MockMvc mvc

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
    private ListingService listingService
    @MockBean
    private ListingRepository repository;

    @Captor
    private ArgumentCaptor<Artist> argumentCaptor

    def "GetAllArtists"() {
        when:
        when(artistService.getAllArtists()).thenReturn(List.of())
        def target = '/concerts/data/artists'

        and:
        def results = mvc.perform(get(target)
                .accept(MediaType.APPLICATION_JSON))

        then:
        results.andExpect(content().string("[]"))

        and:
        results.andExpect(status().isOk())
    }

    def "CreateArtist"() {
        when:
        def target = '/concerts/data/artists'

        and:
        def artist = new ArtistDto(
                "Duran Duran",
                AGENDER,
                1000L,
                LocalDateTime.now().toString(),
                "Birmingham",
                "Great Britain",
                "test")

        and:
        def objectMapper = new ObjectMapper()

        and:
        def results = mvc.perform(post(target)
                .content(objectMapper.writeValueAsString(artist))
                .contentType(MediaType.APPLICATION_JSON))
        then:
        results.andExpect(status().isOk())

        and:
        results.andExpect(content().string(""))
    }
}
