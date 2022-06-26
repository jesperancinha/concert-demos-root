package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.SoftAssertions
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.daos.*
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

import static TestConstants.HEY_MAMA
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
    private ArgumentCaptor<Music> argumentCaptor

    def "GetAllMusics"() {
        when:
        when(musicService.getAllMusics()).thenReturn(List.of())
        def target = '/concerts/data/musics'

        and:
        def results = mvc.perform(get(target)
                .accept(MediaType.APPLICATION_JSON))

        then:
        results.andExpect(content().string("[]"))

        and:
        results.andExpect(status().isOk())
    }

    def "CreateMusic"() {
        when:
        def target = '/concerts/data/musics'

        and:
        def musicDto = new MusicDto(
                1L,
                "Hey mama",
                HEY_MAMA)
        when(musicService.createMusic(musicDto)).thenReturn(musicDto)

        and:
        def objectMapper = new ObjectMapper()

        and:
        def results = mvc.perform(post(target)
                .content(objectMapper.writeValueAsString(musicDto))
                .contentType(MediaType.APPLICATION_JSON))
        then:
        results.andExpect(status().isOk())

        and:
        def contentAsString = results.andReturn().getResponse().getContentAsString()
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(contentAsString).isEqualTo(objectMapper.writeValueAsString(musicDto))

        }
    }
}
