package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.mvc.model.Artist
import org.jesperancinha.concerts.mvc.repos.ArtistRepository
import org.jesperancinha.concerts.mvc.repos.ConcertRepository
import org.jesperancinha.concerts.mvc.repos.ListingRepository
import org.jesperancinha.concerts.mvc.repos.MusicRepository
import org.jesperancinha.concerts.mvc.services.ArtistService
import org.jesperancinha.concerts.mvc.services.ConcertService
import org.jesperancinha.concerts.mvc.services.ListingService
import org.jesperancinha.concerts.mvc.services.MusicService
import org.jesperancinha.concerts.types.Gender.AGENDER
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@WebMvcTest(controllers = [ArtistControllerImpl::class, ArtistController::class])
class ArtistControllerImplMockkTest(
    @Autowired val mvc: MockMvc,
) : WordSpec() {

    override fun extensions() = listOf(SpringExtension)

    @MockkBean
    lateinit var musicService: MusicService

    @MockkBean
    lateinit var musicRepository: MusicRepository

    @MockkBean
    lateinit var artistService: ArtistService

    @MockkBean
    lateinit var artistRepository: ArtistRepository

    @MockkBean
    lateinit var concertService: ConcertService

    @MockkBean
    lateinit var concertRepository: ConcertRepository

    @MockkBean
    lateinit var listingService: ListingService

    @MockkBean
    lateinit var repository: ListingRepository

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Artist>

    init {
        "artist controller integration test" should {
            "retrieve all artists" {
                every { artistService.getAllArtists() } returns listOf()
                val target = "/concerts/data/artists"
                val results = mvc.perform(get(target)
                    .accept(MediaType.APPLICATION_JSON))
                results.andExpect(content().string("[]"))
                results.andExpect(status().isOk)
            }
            "create Artist" {
                val artistDto = ArtistDto(
                    "Duran Duran",
                    AGENDER,
                    1000L,
                    LocalDateTime.now().toString(),
                    "Birmingham",
                    "Great Britain",
                    "test")
                every { artistService.createArtist(artistDto)} returns artistDto
                val target = "/concerts/data/artists"
                val objectMapper = ObjectMapper()
                val valueAsString = objectMapper.writeValueAsString(artistDto)
                val results = mvc.perform(post(target)
                    .content(valueAsString)
                    .contentType(MediaType.APPLICATION_JSON))
                results.andExpect(status().isOk)
                results.andExpect(content().string(valueAsString))
            }
        }
    }
}
