package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
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

@WebMvcTest(controllers = [ArtistControllerImpl::class, ArtistController::class])
class ArtistControllerImplKoITTest(
    @Autowired val mvc: MockMvc,
) : WordSpec() {

    override fun extensions() = listOf(SpringExtension)

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
    lateinit var argumentCaptor: ArgumentCaptor<Artist>


    init {
        "SpringExtension" should {
            "retrieve all mocked artists" {
                `when`(artistService.getAllArtists()).thenReturn(listOf())
                val target = "/concerts/data/artists"
                val results = mvc.perform(get(target)
                    .accept(MediaType.APPLICATION_JSON))
                results.andExpect(content().string("[]"))
                results.andExpect(status().isOk)
            }
            "create Artist" {
                val target = "/concerts/data/artists"
                val artist = ArtistDto(
                    "Duran Duran",
                    AGENDER,
                    1000L,
                    LocalDateTime.now().toString(),
                    "Birmingham",
                    "Great Britain",
                    "test")
                val objectMapper = ObjectMapper()
                val results = mvc.perform(post(target)
                    .content(objectMapper.writeValueAsString(artist))
                    .contentType(MediaType.APPLICATION_JSON))
                results.andExpect(status().isOk)
                results.andExpect(content().string(""))
            }
        }
    }
}
