package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.FORREST_PLACE
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.SPARROW
import org.jesperancinha.concerts.mvc.daos.Listing
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
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
class ListingControllerImplKoTest : WordSpec() {

    @Autowired
    lateinit var mvc: MockMvc

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
    lateinit var argumentCaptor: ArgumentCaptor<Listing>

    override fun extensions(): List<Extension> = listOf(SpringExtension)

    init {
        "Spring Extension" should {
            "retrieve all listings"{
                `when`(listingService.getAllListings()).thenReturn(listOf())
                val target = "/concerts/data/listings"
                val results = mvc.perform(
                    get(target)
                        .accept(MediaType.APPLICATION_JSON)
                )
                results.andExpect(content().string("[]"))
                results.andExpect(status().isOk)
            }
            "create listing" {
                val target = "/concerts/data/listings"
                val musicDto = MusicDto(
                    1L,
                    "Hey mama",
                    "HEY_MAMA"
                )
                val artistDto = ArtistDto(
                    name = SPARROW,
                    gender = FEMALE,
                    careerStart = 1000L,
                    birthDate = LocalDateTime.now().toString(),
                    birthCity = FORREST_PLACE,
                    country = "The Kingdom Land",
                    keywords = "Rap"
                )
                val listingDto = ListingDto(
                    1L,
                    artistDto,
                    musicDto,
                    mutableListOf()
                )
                val objectMapper = ObjectMapper()
                `when`(listingService.createListing(listingDto)).thenReturn(listingDto)
                val results = mvc.perform(
                    post(target)
                        .content(objectMapper.writeValueAsString(listingDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                results
                    .andExpect(status().isOk)
                    .andExpect(content().string(objectMapper.writeValueAsString(listingDto)))
            }
        }
    }

}
