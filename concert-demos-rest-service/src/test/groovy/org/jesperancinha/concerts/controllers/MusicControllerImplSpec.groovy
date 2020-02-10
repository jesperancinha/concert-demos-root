package org.jesperancinha.concerts.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.model.Music
import org.jesperancinha.concerts.services.MusicService
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import reactor.core.publisher.Flux
import spock.lang.Specification

import static org.jesperancinha.concerts.controllers.TestConstants.HEY_MAMA
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [MusicControllerImpl, MusicController])
class MusicControllerImplSpec extends Specification {

    @Autowired
    private MockMvc mvc

    @MockBean
    private MusicService artistService

    @MockBean
    private DatabaseClient databaseClient

    @Captor
    private ArgumentCaptor<Music> argumentCaptor;

    def "GetAllMusics"() {
        when:
        when(artistService.getAllMusics()).thenReturn(Flux.just(new Music[0]))
        def target = '/concerts/data/musics'

        and:
        def results = mvc.perform(get(target)
                .accept(MediaType.APPLICATION_JSON))

        then:
        results.andExpect(content().string(""))

        and:
        results.andExpect(status().isOk())
    }

    def "CreateMusic"() {
        when:
        def target = '/concerts/data/musics'

        and:
        def artist = new MusicDto(
                1L,
                "Hey mama",
                HEY_MAMA)

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
