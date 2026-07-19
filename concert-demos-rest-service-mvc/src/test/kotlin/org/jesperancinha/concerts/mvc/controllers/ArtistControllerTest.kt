package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.mvc.daos.*
import org.jesperancinha.concerts.mvc.services.ArtistService
import org.jesperancinha.concerts.mvc.services.ConcertService
import org.jesperancinha.concerts.mvc.services.ListingService
import org.jesperancinha.concerts.mvc.services.MusicService
import org.jesperancinha.concerts.types.Gender.NONAPPLICABLE
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ArtistControllerTest @Autowired constructor(
    private val mvc: MockMvc,
    @MockitoBean private val musicService: MusicService,
    @MockitoBean private val musicRepository: MusicRepository,
    @MockitoBean private val artistService: ArtistService,
    @MockitoBean private val artistRepository: ArtistRepository,
    @MockitoBean private val concertService: ConcertService,
    @MockitoBean private val concertRepository: ConcertRepository,
    @MockitoBean private val listingService: ListingService,
    @MockitoBean private val listingRepository: ListingRepository,
) {
    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Artist>

    @Test
    fun `Retrieve all mocked artists`() {
        `when`(artistService.getAllArtists()).thenReturn(listOf())
        val target = "/concerts/data/artists"
        val results = mvc.perform(
            get(target)
                .accept(MediaType.APPLICATION_JSON)
        )
        results.andExpect(content().string("[]"))
        results.andExpect(status().isOk)
    }


    @Test
    fun `Create Artist`() {
        val target = "/concerts/data/artists"
        val artist = ArtistDto(
            name = "Duran Duran",
            gender = NONAPPLICABLE,
            careerStart = 1000L,
            birthDate = LocalDateTime.now().toString(),
            birthCity = "Birmingham",
            country = "Great Britain",
            keywords = "test"
        )
        val objectMapper = ObjectMapper()
        val results = mvc.perform(
            post(target)
                .content(objectMapper.writeValueAsString(artist))
                .contentType(MediaType.APPLICATION_JSON)
        )
        results.andExpect(status().isOk)
        results.andExpect(content().string(""))
    }

    @AfterEach
    fun tearDown() {
        verifyNoInteractions(musicService)
        verifyNoInteractions(concertService)
        verifyNoInteractions(listingService)
        verifyNoInteractions(listingRepository)
    }
}
