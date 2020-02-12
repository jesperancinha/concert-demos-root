package org.jesperancinha.concerts.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.model.Concert
import org.jesperancinha.concerts.services.ConcertService
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification

import java.time.LocalDateTime

import static org.jesperancinha.concerts.controllers.TestConstants.HEY_MAMA
import static org.jesperancinha.concerts.types.Gender.FEMALE
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [ConcertControllerImpl, ConcertController])
class ConcertControllerImplSpec extends Specification {

    @Autowired
    private MockMvc mvc

    @MockBean
    private ConcertService concertService

    @Captor
    private ArgumentCaptor<Concert> argumentCaptor

    def "GetAllConcerts"() {
        when:
        when(concertService.getAllConcerts()).thenReturn(Flux.just(new Concert[0]))
        def target = '/concerts/data/concerts'

        and:
        def results = mvc.perform(get(target)
                .accept(MediaType.APPLICATION_JSON))

        then:
        results.andExpect(content().string(""))

        and:
        results.andExpect(status().isOk())
    }

    def "CreateConcert"() {
        when:
        def target = '/concerts/data/concerts'

        and:
        def musicDto = new MusicDto(
                1,
                "Hey mama",
                HEY_MAMA)
        def artistDto = new ArtistDto(
                1,
                "Nicky Minaj",
                FEMALE,
                1000L,
                LocalDateTime.now().toString(),
                "Trinidad en Tobago City",
                "Trinidad en Tobago",
                "Rap")
        def listingDto = new ListingDto(
                1,
                artistDto,
                musicDto,
                List.of(musicDto)
        )
        def concertDto = new ConcertDto(
                "Nicki Wrld Tour",
                "Amsterdam",
                LocalDateTime.of(2019, 3, 25, 0, 0, 0).toString(),
                List.of(listingDto)
        )

        and:
        def objectMapper = new ObjectMapper()
        and:
        when(concertService.createConcert(concertDto)).thenReturn(Mono.just(concertDto))
        and:
        def results = mvc.perform(post(target)
                .content(objectMapper.writeValueAsString(concertDto))
                .contentType(MediaType.APPLICATION_JSON))
        then:
        results.andExpect(status().isOk())

        and:
        results.andExpect(content().string(""))
    }
}
