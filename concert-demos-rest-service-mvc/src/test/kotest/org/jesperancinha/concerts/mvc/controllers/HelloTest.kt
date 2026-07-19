package org.jesperancinha.concerts.mvc.controllers

import io.kotest.core.spec.style.WordSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldNotBe
import org.jesperancinha.concerts.mvc.daos.ArtistRepository
import org.jesperancinha.concerts.mvc.services.ArtistServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.bean.override.mockito.MockitoBean

@ContextConfiguration(classes = [(ArtistServiceImpl::class)])
class HelloTest @Autowired constructor(
    private val service: ArtistServiceImpl,
    @MockitoBean private val artistRepository: ArtistRepository,
) : WordSpec() {

    override fun extensions() = listOf(SpringExtension)

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


