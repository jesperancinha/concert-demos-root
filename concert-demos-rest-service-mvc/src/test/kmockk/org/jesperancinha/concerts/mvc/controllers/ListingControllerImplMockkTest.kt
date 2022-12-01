package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.every
import io.mockk.verify
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.HEY_MAMA
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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@MockkBean(
    classes = [
        MusicService::class, MusicRepository::class,
        MusicRepository::class, ArtistRepository::class,
        ArtistService::class, ConcertService::class,
        ConcertRepository::class, ListingService::class,
        ListingRepository::class]
)
class ListingControllerImplMockkTest : WordSpec() {
    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var listingService: ListingService
    override fun extensions() = listOf(SpringExtension)

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Listing>

    init {
        "Spring Extension" should {
            "retrieve all listings"{
                every { listingService.getAllListings() } returns listOf()
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
                    HEY_MAMA
                )
                val artistDto = ArtistDto(
                    name = "Nicky Minaj",
                    gender = FEMALE,
                    careerStart = 1000L,
                    birthDate = LocalDateTime.now().toString(),
                    birthCity = "Port of Spain",
                    country = "Trinidad en Tobago",
                    keywords = "Rap"
                )
                val listingDto = ListingDto(
                    1L,
                    artistDto,
                    musicDto,
                    mutableListOf()
                )
                val objectMapper = ObjectMapper()
                every { listingService.createListing(listingDto) } returns listingDto
                val results = mvc.perform(
                    post(target)
                        .content(objectMapper.writeValueAsString(listingDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                results
                    .andExpect(status().isOk)
                    .andExpect(content().string(objectMapper.writeValueAsString(listingDto)))

                verify(exactly = 1) { listingService.createListing(listingDto = listingDto) }
            }
        }
    }
}
