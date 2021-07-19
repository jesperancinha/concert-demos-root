package org.jesperancinha.concerts.mvc.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.verify
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.mvc.controllers.TestKUtils.Companion.HEY_MAMA
import org.jesperancinha.concerts.mvc.model.Music
import org.jesperancinha.concerts.mvc.repos.ArtistRepository
import org.jesperancinha.concerts.mvc.repos.ConcertRepository
import org.jesperancinha.concerts.mvc.repos.ListingRepository
import org.jesperancinha.concerts.mvc.repos.MusicRepository
import org.jesperancinha.concerts.mvc.services.ArtistService
import org.jesperancinha.concerts.mvc.services.ConcertService
import org.jesperancinha.concerts.mvc.services.ListingService
import org.jesperancinha.concerts.mvc.services.MusicService
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

@WebMvcTest(controllers = [MusicControllerImpl::class, MusicController::class])
class MusicControllerImplMockkTest(
    @Autowired
    val mvc: MockMvc,
) : WordSpec() {

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
    lateinit var argumentCaptor: ArgumentCaptor<Music>

    override fun extensions() = listOf(SpringExtension)

    init {
        "Spring Extension" should {
            "retrieve all music" {
                every { musicService.getAllMusics() } returns listOf()
                val target = "/concerts/data/musics"
                val results = mvc.perform(get(target)
                    .accept(MediaType.APPLICATION_JSON))
                results.andExpect(content().string("[]"))
                results.andExpect(status().isOk)
            }

            "create music"{
                val target = "/concerts/data/musics"
                val musicDto = MusicDto(
                    1L,
                    "Hey mama",
                    HEY_MAMA)
                every { musicService.createMusic(musicDto) } returns musicDto
                val objectMapper = ObjectMapper()
                val results = mvc.perform(post(target)
                    .content(objectMapper.writeValueAsString(musicDto))
                    .contentType(MediaType.APPLICATION_JSON))
                results.andExpect(status().isOk)
                val contentAsString = results.andReturn().response.contentAsString

                contentAsString shouldBe objectMapper.writeValueAsString(musicDto)

                verify(exactly = 1) { musicService.createMusic(music = musicDto) }
            }
        }
    }
}
