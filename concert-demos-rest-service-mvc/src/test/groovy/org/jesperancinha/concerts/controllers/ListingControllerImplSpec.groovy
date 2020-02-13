package org.jesperancinha.concerts.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.SoftAssertions
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.data.ListingDto
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.model.Listing
import org.jesperancinha.concerts.services.ListingService
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.time.LocalDateTime

import static org.jesperancinha.concerts.controllers.TestConstants.HEY_MAMA
import static org.jesperancinha.concerts.types.Gender.FEMALE
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [ListingControllerImpl, ListingController])
class ListingControllerImplSpec extends Specification {

    @Autowired
    private MockMvc mvc

    @MockBean
    private ListingService listingService

    @Captor
    private ArgumentCaptor<Listing> argumentCaptor

    def "GetAllListings"() {
        when:
        when(listingService.getAllListings()).thenReturn(List.of())
        def target = '/concerts/data/listings'

        and:
        def results = mvc.perform(get(target)
                .accept(MediaType.APPLICATION_JSON))

        then:
        results.andExpect(content().string("[]"))

        and:
        results.andExpect(status().isOk())
    }

    def "CreateListing"() {
        when:
        def target = '/concerts/data/listings'

        and:
        def musicDto = new MusicDto(
                1L,
                "Hey mama",
                HEY_MAMA)
        def artistDto = new ArtistDto(
                "Nicky Minaj",
                FEMALE,
                1000L,
                LocalDateTime.now().toString(),
                "Port of Spain",
                "Trinidad en Tobago",
                "Rap")
        def listingDto = new ListingDto(
                1L,
                artistDto,
                musicDto,
                List.of(musicDto)
        )

        and:
        def objectMapper = new ObjectMapper()
        and:
        when(listingService.createListing(listingDto)).thenReturn(listingDto)
        and:
        def results = mvc.perform(post(target)
                .content(objectMapper.writeValueAsString(listingDto))
                .contentType(MediaType.APPLICATION_JSON))
        then:
        results.andExpect(status().isOk())

        and:
        def contentAsString = results.andReturn().getResponse().getContentAsString()
        def value = objectMapper.readValue(contentAsString, ListingDto)
        SoftAssertions.assertSoftly { softly ->
            softly.assertThat(value).isEqualTo(listingDto)

        }
    }
}
