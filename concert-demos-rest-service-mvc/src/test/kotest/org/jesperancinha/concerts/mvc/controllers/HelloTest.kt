package org.jesperancinha.concerts.mvc.controllers

import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldNotBe
import org.jesperancinha.concerts.mvc.repos.ArtistRepository
import org.jesperancinha.concerts.mvc.services.ArtistServiceImpl
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [(ArtistServiceImpl::class)])
class HelloTest(service: ArtistServiceImpl) : WordSpec() {

    override fun extensions() = listOf(SpringExtension)

    @MockBean
    lateinit var artistRepository: ArtistRepository

    init {
        "SpringExtension" should {
            "have autowired the service" {
                artistRepository shouldNotBe null
                service shouldNotBe null
                service.getAllArtists() shouldNotBe null
                service.getAllArtists()?.shouldBeEmpty()
            }
        }
    }
}


