package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.matchers.shouldBe
import io.kotest.mpp.timeInMillis
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.HEY_MAMA
import org.jesperancinha.concerts.mvc.model.Music
import org.jesperancinha.concerts.mvc.repos.ArtistRepository
import org.jesperancinha.concerts.mvc.repos.ConcertRepository
import org.jesperancinha.concerts.mvc.repos.ListingRepository
import org.jesperancinha.concerts.mvc.repos.MusicRepository
import org.jesperancinha.concerts.mvc.services.ArtistService
import org.jesperancinha.concerts.mvc.services.ConcertService
import org.jesperancinha.concerts.mvc.services.ListingService
import org.jesperancinha.concerts.mvc.services.MusicService
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [MusicControllerImpl::class, MusicController::class])
class MusicControllerImplTest(
    @Autowired
    val mvc: MockMvc,
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
    lateinit var repository: ListingRepository;

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Music>

    @Test
    fun `retrieve all music`() {
        `when`(musicService.getAllMusics()).thenReturn(listOf())
        val target = "/concerts/data/musics"
        val results = mvc.perform(get(target)
            .accept(MediaType.APPLICATION_JSON))
        results.andExpect(content().string("[]"))
        results.andExpect(status().isOk())
    }

    @Test
    fun `create music`() {
        val target = "/concerts/data/musics"
        val musicDto = MusicDto(
            1L,
            "Hey mama",
            HEY_MAMA)
        `when`(musicService.createMusic(musicDto)).thenReturn(musicDto)
        val objectMapper = ObjectMapper()
        val results = mvc.perform(post(target)
            .content(objectMapper.writeValueAsString(musicDto))
            .contentType(MediaType.APPLICATION_JSON))
        results.andExpect(status().isOk)
        val contentAsString = results.andReturn().response.contentAsString

        contentAsString shouldBe objectMapper.writeValueAsString(musicDto)
        verify(musicService, only()).createMusic(musicDto)
    }

}
