package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.HEY_MAMA
import org.jesperancinha.concerts.mvc.model.Concert
import org.jesperancinha.concerts.mvc.repos.ArtistRepository
import org.jesperancinha.concerts.mvc.repos.ConcertRepository
import org.jesperancinha.concerts.mvc.repos.ListingRepository
import org.jesperancinha.concerts.mvc.repos.MusicRepository
import org.jesperancinha.concerts.mvc.services.ArtistService
import org.jesperancinha.concerts.mvc.services.ConcertService
import org.jesperancinha.concerts.mvc.services.ListingService
import org.jesperancinha.concerts.mvc.services.MusicService
import org.jesperancinha.concerts.types.Gender.FEMALE
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
class ConcertControllerImplKoTest(
    @Autowired val mvc: MockMvc,
) : WordSpec() {
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

    override fun extensions() = listOf(SpringExtension)

    init {
        "concert controller extended integration tests" should {
            "retrieve all concerts" {
                val target = "/concerts/data/concerts"
                `when`(concertService.getAllConcerts()).thenReturn(listOf())
                val results = mvc.perform(
                    get(target)
                        .accept(MediaType.APPLICATION_JSON)
                )
                results.andExpect(content().string("[]"))
                results.andExpect(status().isOk)
            }
            "create concert"{
                val target = "/concerts/data/concerts"
                val musicDto = MusicDto(
                    1,
                    "Hey mama",
                    HEY_MAMA
                )
                val artistDto = ArtistDto(
                    id = 1,
                    name = "Nicky Minaj",
                    gender = FEMALE,
                    careerStart = 1000L,
                    birthDate = LocalDateTime.now().toString(),
                    birthCity = "Port of Spain",
                    country = "Trinidad en Tobago",
                    keywords = "Rap"
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
    }
}
