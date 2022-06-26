package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.mvc.daos.*
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
@MockkBean(
    classes = [
        MusicService::class, MusicRepository::class,
        MusicRepository::class, ArtistRepository::class,
        ArtistService::class, ConcertService::class,
        ConcertRepository::class, ListingService::class,
        ListingRepository::class]
)
class ArtistControllerImplMockkTest : WordSpec() {

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var artistService: ArtistService

    override fun extensions() = listOf(SpringExtension)

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Artist>

    init {
        "artist controller integration test" should {
            "retrieve all artists" {
                every { artistService.getAllArtists() } returns listOf()
                val target = "/concerts/data/artists"
                val results = mvc.perform(
                    get(target)
                        .accept(MediaType.APPLICATION_JSON)
                )
                results.andExpect(content().string("[]"))
                results.andExpect(status().isOk)
            }
            "create Artist" {
                val artistDto = ArtistDto(
                    name = "Duran Duran",
                    gender = AGENDER,
                    careerStart = 1000L,
                    birthDate = LocalDateTime.now().toString(),
                    birthCity = "Birmingham",
                    country = "Great Britain",
                    keywords = "test"
                )
                every { artistService.createArtist(artistDto) } returns artistDto
                val target = "/concerts/data/artists"
                val objectMapper = ObjectMapper()
                val valueAsString = objectMapper.writeValueAsString(artistDto)
                val results = mvc.perform(
                    post(target)
                        .content(valueAsString)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                results.andExpect(status().isOk)
                results.andExpect(content().string(valueAsString))
            }
        }
    }
}
