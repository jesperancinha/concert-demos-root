package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.HEY_MAMA
import org.jesperancinha.concerts.mvc.daos.*
import org.jesperancinha.concerts.mvc.services.ArtistService
import org.jesperancinha.concerts.mvc.services.ConcertService
import org.jesperancinha.concerts.mvc.services.ListingService
import org.jesperancinha.concerts.mvc.services.MusicService
import org.jesperancinha.concerts.types.Gender.FEMALE
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@WebMvcTest(controllers = [ConcertControllerImpl::class, ConcertController::class])
class ConcertControllerImplTest(
    @Autowired val mvc: MockMvc,
) {
    @MockBean
    lateinit var musicService: MusicService

    @MockBean
    lateinit var musicRepository: MusicRepository

    @MockBean
    lateinit var artistService: ArtistService

    @MockBean
    lateinit var artistRepository: ArtistRepository

    @MockBean
    lateinit var concertService: ConcertService

    @MockBean
    lateinit var concertRepository: ConcertRepository

    @MockBean
    lateinit var listingService: ListingService

    @MockBean
    lateinit var repository: ListingRepository

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Concert>

    @Test
    fun `Retrieve all concerts`() {
        val target = "/concerts/data/concerts"
        `when`(concertService.getAllConcerts()).thenReturn(listOf())
        val results = mvc.perform(
            get(target)
                .accept(MediaType.APPLICATION_JSON)
        )
        results.andExpect(content().string("[]"))
        results.andExpect(status().isOk())
    }

    @Test
    fun `Create concert`() {
        val target = "/concerts/data/concerts"
        val musicDto = MusicDto(
            1,
            "Hey mama",
            HEY_MAMA
        )
        val artistDto = ArtistDto(
            1,
            "Nicky Minaj",
            FEMALE,
            1000L,
            LocalDateTime.now().toString(),
            "Port of Spain",
            "Trinidad en Tobago",
            "Rap"
        )
        val listingDto = ListingDto(
            1,
            artistDto,
            musicDto,
            mutableListOf(musicDto)
        )
        val concertDto = ConcertDto(
            name = "Nicki Wrld Tour",
            location = "Amsterdam",
            date = LocalDateTime.of(2019, 3, 25, 0, 0, 0).toString(),
            listingDtos = mutableListOf(listingDto)
        )
        val objectMapper = ObjectMapper()
        `when`(concertService.createConcert(concertDto)).thenReturn(concertDto)
        val results = mvc.perform(
            post(target)
                .content(objectMapper.writeValueAsString(concertDto))
                .contentType(MediaType.APPLICATION_JSON)
        )
        results.andExpect(status().isOk)
        results.andExpect(content().string(objectMapper.writeValueAsString(concertDto)))
    }
}
