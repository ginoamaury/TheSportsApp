package com.ginoamaury.domain.usecases

import com.ginoamaury.domain.data.repositories.TeamRepository
import com.ginoamaury.domain.model.Event
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config

@RunWith(MockitoJUnitRunner::class)
@Config(manifest = Config.NONE)
class GetEventsByTeamTest {

    private val events = listOf(
        Event(
            "1",
            "event N1",
            "date",
            "urlImg",
            "1"
        ),
        Event(
            "2",
            "event N2",
            "date",
            "urlImg",
            "1"
        )
    )

    @Mock
    lateinit var teamRepository: TeamRepository

    @InjectMocks
    lateinit var getEventsByTeam: GetEventsByTeam

    @Test
    fun invokeTest(){
        val idTeam = "133601"
        runBlocking {
            Mockito.`when`(teamRepository.getNextEventsByTeam(idTeam)).thenReturn(events)
            val result = getEventsByTeam(idTeam)
            Mockito.verify(teamRepository,Mockito.times(1)).getNextEventsByTeam(idTeam)
            Assert.assertEquals(events,result)
        }

    }

}