package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.mvc.daos.*
import org.jesperancinha.concerts.mvc.services.ArtistService
import org.jesperancinha.concerts.mvc.services.ConcertService
import org.jesperancinha.concerts.mvc.services.ListingService
import org.jesperancinha.concerts.mvc.services.MusicService
import org.jesperancinha.concerts.types.Gender.NONAPPLICABLE
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ArtistControllerImplKoTest(
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
        "artist controller integration test" should {
            "retrieve all artists" {
                `when`(artistService.getAllArtists()).thenReturn(listOf())
                val target = "/concerts/data/artists"
                val results = mvc.perform(
                    get(target)
                        .accept(MediaType.APPLICATION_JSON)
                )
                results.andExpect(content().string("[]"))
                results.andExpect(status().isOk)
            }
            "create Artist" {
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
        }
    }
}
