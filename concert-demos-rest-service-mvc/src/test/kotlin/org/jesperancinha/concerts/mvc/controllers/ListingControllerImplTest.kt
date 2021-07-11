package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.matchers.shouldBe
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
import org.junit.jupiter.api.Test
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

@WebMvcTest(controllers = [ListingControllerImpl::class, ListingController::class])
class ListingControllerImplTest(
    @Autowired val mvc: MockMvc,
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
    lateinit var repository: ListingRepository

    @Captor
    lateinit var argumentCaptor: ArgumentCaptor<Listing>

    @Test
    fun `retrieve all listings`() {
        `when`(listingService.getAllListings()).thenReturn(listOf())
        val target = "/concerts/data/listings"
        val results = mvc.perform(get(target)
            .accept(MediaType.APPLICATION_JSON))
        results.andExpect(content().string("[]"))
        results.andExpect(status().isOk)
    }

    @Test
    fun `create listing`() {
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
        `when`(listingService.createListing(listingDto)).thenReturn(listingDto)
        val results = mvc.perform(post(target)
            .content(objectMapper.writeValueAsString(listingDto))
            .contentType(MediaType.APPLICATION_JSON))
        results.andExpect(status().isOk)
        val contentAsString = results.andReturn().response.contentAsString
        val value = objectMapper.readValue(contentAsString, ListingDto::class.java)

        value shouldBe listingDto
    }
}
