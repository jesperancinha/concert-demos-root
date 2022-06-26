package org.jesperancinha.concerts.mvc.controllers

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import org.jesperancinha.concerts.mvc.daos.ArtistRepository
import org.jesperancinha.concerts.mvc.services.ArtistServiceImpl
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [(ArtistServiceImpl::class)])
class HelloMockkTest(service: ArtistServiceImpl) : WordSpec() {

    override fun extensions() = listOf(SpringExtension)

    @MockkBean
    lateinit var artistRepository: ArtistRepository

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


