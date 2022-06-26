package org.jesperancinha.concerts.mvc.controllers


import org.jesperancinha.concerts.mvc.daos.Concert
import org.jesperancinha.concerts.mvc.daos.ArtistRepository
import org.jesperancinha.concerts.mvc.daos.ConcertRepository
import org.jesperancinha.concerts.mvc.daos.ListingRepository
import org.jesperancinha.concerts.mvc.daos.MusicRepository
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

import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [ConcertControllerImpl, ConcertController])
class ConcertControllerImplSpec extends Specification {

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
    private ArgumentCaptor<Concert> argumentCaptor

    def "GetAllConcerts"() {
        when:
        when(concertService.getAllConcerts()).thenReturn(List.of())
        def target = '/concerts/data/concerts'

        and:
        def results = mvc.perform(get(target)
                .accept(MediaType.APPLICATION_JSON))

        then:
        results.andExpect(content().string("[]"))

        and:
        results.andExpect(status().isOk())
    }

    def "CreateConcert"() {
//        when:
//        def target = '/concerts/data/concerts'
//
//        and:
//        def musicDto = new MusicDto(
//                1,
//                "Hey mama",
//                HEY_MAMA)
//        def artistDto = new ArtistDto(
//                1,
//                "Nicky Minaj",
//                FEMALE,
//                1000L,
//                LocalDateTime.now().toString(),
//                "Port of Spain",
//                "Trinidad en Tobago",
//                "Rap")
//        def listingDto = new ListingDto(
//                1,
//                artistDto,
//                musicDto,
//                List.of(musicDto)
//        )
//        def concertDto = new ConcertDto(
//                "Nicki Wrld Tour",
//                "Amsterdam",
//                LocalDateTime.of(2019, 3, 25, 0, 0, 0).toString(),
//                List.of(listingDto)
//        )
//
//        and:
//        def objectMapper = new ObjectMapper()
//        and:
//        when(concertService.createConcert(concertDto)).thenReturn(concertDto)
//        and:
//        def results = mvc.perform(post(target)
//                .content(objectMapper.writeValueAsString(concertDto))
//                .contentType(MediaType.APPLICATION_JSON))
//        then:
//        results.andExpect(status().isOk())
//
//        and:
//        results.andExpect(content().string(""))
    }
}
