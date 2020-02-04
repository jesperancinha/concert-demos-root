package org.jesperancinha.concerts.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.concerts.model.Artist
import org.jesperancinha.concerts.services.ArtistService
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import reactor.core.publisher.Flux
import spock.lang.Specification

import java.time.LocalDateTime

import static org.jesperancinha.concerts.model.Gender.AGENDER
import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [ArtistControllerImpl, ArtistController])
class ArtistControllerImplSpec extends Specification {

    @Autowired
    private MockMvc mvc

    @MockBean
    private ArtistService artistService

    @Captor
    private ArgumentCaptor<Artist> argumentCaptor;

    def "GetAllArtists"() {
        when:
        when(artistService.getAllArtists()).thenReturn(Flux.just(new Artist[0]))
        def target = '/concerts/data/artists'
        and:
        def results = mvc.perform(get(target)
                .accept(MediaType.APPLICATION_JSON))
        then:
        results.andExpect(content().string(""))
        and:
        results.andExpect(status().isOk())
    }

    def "CreateArtist"() {
        when:
        def target = '/concerts/data/artists'
        and:
        def artist = new Artist(
                "Duran Duran",
                AGENDER,
                1000L,
                LocalDateTime.now().toString(),
                "Birmingham",
                "Great Britain",
                "test")
        and:
        def objectMapper = new ObjectMapper();
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
