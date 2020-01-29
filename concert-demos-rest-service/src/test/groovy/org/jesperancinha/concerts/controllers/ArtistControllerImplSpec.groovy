package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.services.ArtistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@AutoConfigureMockMvc
@WebMvcTest(ArtistController.class)
class ArtistControllerImplSpec extends Specification {

    @Autowired
    private MockMvc mvc

    @MockBean
    private ArtistService artistService

    def "GetAllArtists"() {
        expect:
        Math.max(a, b) == c
        where:
        a << [3, 5, 9]
        b << [7, 4, 9]
        c << [7, 5, 9]
    }

    def "CreateArtist"() {
        expect:
        Math.max(a, b) == c

        where:
        a << [3, 5, 9]
        b << [7, 4, 9]
        c << [7, 5, 9]
    }
}
