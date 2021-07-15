package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.HEY_MAMA
import org.jesperancinha.concerts.mvc.model.Listing
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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@WebMvcTest(controllers = [ListingControllerImpl::class, ListingController::class])
class ListingControllerImplMockkTest(
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
    lateinit var argumentCaptor: ArgumentCaptor<Listing>

    init {
        "Spring Extension" should {
            "retrieve all listings"{
                every { listingService.getAllListings() } returns listOf()
                val target = "/concerts/data/listings"
                val results = mvc.perform(get(target)
                    .accept(MediaType.APPLICATION_JSON))
                results.andExpect(content().string("[]"))
                results.andExpect(status().isOk)
            }
            "create listing" {
                val target = "/concerts/data/listings"
                val musicDto = MusicDto(
                    1L,
                    "Hey mama",
                    HEY_MAMA)
                val artistDto = ArtistDto(
                    "Nicky Minaj",
                    FEMALE,
                    1000L,
                    LocalDateTime.now().toString(),
                    "Port of Spain",
                    "Trinidad en Tobago",
                    "Rap")
                val listingDto = ListingDto(
                    1L,
                    artistDto,
                    musicDto,
                    mutableListOf()
                )
                val objectMapper = ObjectMapper()
                every { listingService.createListing(listingDto) } returns listingDto
                val results = mvc.perform(post(target)
                    .content(objectMapper.writeValueAsString(listingDto))
                    .contentType(MediaType.APPLICATION_JSON))
                results
                    .andExpect(status().isOk)
                    .andExpect(content().string(objectMapper.writeValueAsString(listingDto)))
            }
        }
    }

}
