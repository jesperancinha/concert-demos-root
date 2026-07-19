package org.jesperancinha.concerts.mvc.controllers

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import org.jesperancinha.concerts.mvc.daos.ArtistRepository
import org.jesperancinha.concerts.mvc.services.ArtistServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [(ArtistServiceImpl::class)])
class HelloMockkTest @Autowired constructor(
    private val service: ArtistServiceImpl,
    @MockkBean private val artistRepository: ArtistRepository,
) : WordSpec() {

    override fun extensions() = listOf(SpringExtension)

    init {
        "SpringExtension" should {
            "have autowired the service" {
                every { artistRepository.findAll() } returns listOf()
                artistRepository shouldNotBe null
                service shouldNotBe null
                service.getAllArtists() shouldNotBe null
                service.getAllArtists()?.shouldBeEmpty()
            }
        }
    }
}


