package org.jesperancinha.concerts.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.concerts.data.MusicDto
import org.jesperancinha.concerts.model.Music
import org.jesperancinha.concerts.services.MusicService
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import reactor.core.publisher.Flux
import spock.lang.Specification

import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [MusicControllerImpl, MusicController])
class MusicControllerImplSpec extends Specification {

    @Autowired
    private MockMvc mvc

    @MockBean
    private MusicService artistService

    @MockBean
    private DatabaseClient databaseClient

    @Captor
    private ArgumentCaptor<Music> argumentCaptor;

    def "GetAllMusics"() {
        when:
        when(artistService.getAllMusics()).thenReturn(Flux.just(new Music[0]))
        def target = '/concerts/data/musics'

        and:
        def results = mvc.perform(get(target)
                .accept(MediaType.APPLICATION_JSON))

        then:
        results.andExpect(content().string(""))

        and:
        results.andExpect(status().isOk())
    }

    def "CreateMusic"() {
        when:
        def target = '/concerts/data/musics'

        and:
        def artist = new MusicDto(
                1L,
                "Hey mama",
                """
Be my woman, girl, I'mma
Be your man
Be my woman, girl, I'll
Be your man
Yes I be your woman
Yes I be your baby
Yes I be whatever that you tell me when you ready
Yes I be your girl, forever your lady
You ain't never gotta worry, I'm down for you baby
Best believe that when you need that
I'll provide that you will always have it
I'll be on deck keep it in check
When you need that I'mma let you have it
You beatin' drum like dum di di dey
I love the dirty rhythm you play
I wanna hear you calling my name
Like hey mama mama hey mama mama (Hey)
Banging the drum like dum di di dey
I know you want it in the worst way
I wanna hear you calling my name
Like hey mama mama hey mama mama (Hey)
Be my woman, girl, I'mma
Be your man
Be my woman, girl, I'll
Be your man
Yes I do the cooking
Yes I do the cleaning
Yes I keep the nana real sweet for your eating
Yes you be the boss yes I be respecting
Whatever that you tell me 'cause it's game you be spitting
Best believe that when you need that
I'll provide that you will always have it
I'll be on deck keep it in check
When you need that I'mma let you have it
You beatin' my drum like dum di di dey
I love the dirty rhythm you play
I wanna hear you calling my name
Like hey mama mama hey mama mama (Hey)
Banging the drum like dum di di dey
I know you want it in the worst way
I wanna hear you calling my name
Like hey mama mama hey mama mama (Hey)
Be my woman, girl, I'mma
Be your man
Be my woman, girl, I'll
Be your man
Whole crew got the juice
Your dick game the truth
My screams is the proof
Them other dudes get the deuce
I might speed in the coupe
Leaving this interview
It ain't nothin' new, I been fuckin' with you
None of them bitches ain't taking you,
Just tell them to make a U (Make a U)
Huh, that how it be, I come first like debuts, huh
So baby when you need that, give me that word
I'm no good, I'll be bad for my baby
So I make sure that he's getting his share
So I make sure that his baby take care
So I make sure mama, toes on my knees
Keep him, please, rub him down, be a lady and a freak
You beatin' my drum like dum di di dey
I love the dirty rhythm you play
I wanna hear you calling my name
Like hey mama mama hey mama mama (Hey)
Banging the drum like dum di di dey
I know you want it in the worst way
I wanna hear you calling my name
Like hey mama mama mama hey mama mama (Hey)
Be my woman, girl, I'mma
Be your man
Be my woman, girl, I'll
Be your man
""")

        and:
        def objectMapper = new ObjectMapper()

        and:
        def results = mvc.perform(post(target)
                .content(objectMapper.writeValueAsString(artist))
                .contentType(MediaType.APPLICATION_JSON))
        then:
        results.andExpect(status().isOk())

        and:
        results.andExpect(content().string(""))
    }
}
