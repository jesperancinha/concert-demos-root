package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.FORREST_PLACE
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.LYRICS_TEXT
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.SPARROW
import org.jesperancinha.concerts.mvc.daos.Concert
import org.jesperancinha.concerts.mvc.daos.ArtistRepository
import org.jesperancinha.concerts.mvc.daos.ConcertRepository
import org.jesperancinha.concerts.mvc.daos.ListingRepository
import org.jesperancinha.concerts.mvc.daos.MusicRepository
import org.jesperancinha.concerts.mvc.services.ArtistService
import org.jesperancinha.concerts.mvc.services.ConcertService
import org.jesperancinha.concerts.mvc.services.ListingService
import org.jesperancinha.concerts.mvc.services.MusicService
import org.jesperancinha.concerts.types.Gender.FEMALE
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
            "create concert" {
                val target = "/concerts/data/concerts"
                val musicDto = MusicDto(
                    1,
                    "Hey mama",
                    LYRICS_TEXT
                )
                val artistDto = ArtistDto(
                    id = 1,
                    name = SPARROW,
                    gender = FEMALE,
                    careerStart = 1000L,
                    birthDate = LocalDateTime.now().toString(),
                    birthCity = FORREST_PLACE,
                    country = "The Kingdom Land",
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
