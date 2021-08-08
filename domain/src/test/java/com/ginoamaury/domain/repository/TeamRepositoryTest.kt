package com.ginoamaury.domain.repository

import com.ginoamaury.domain.data.repositories.TeamRepository
import com.ginoamaury.domain.data.sources.ILocalSource
import com.ginoamaury.domain.data.sources.IRemoteSource
import com.ginoamaury.domain.model.Event
import com.ginoamaury.domain.model.Team
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
class TeamRepositoryTest {
    @Mock
    private lateinit var localSource: ILocalSource

    @Mock
    private lateinit var remoteSource: IRemoteSource

    @InjectMocks
    private lateinit var repository: TeamRepository

    private val teams = listOf(
        Team(
            "1",
            "barcelona",
            "urlStadium",
            "urlBadge",
            "description",
            "1997",
            "jersey",
            "urlWebsite",
            "facebook",
            "instagram",
            "twitter",
            "youtube",
            "league"
        ),
        Team(
            "2",
            "Madrid",
            "urlStadium",
            "urlBadge",
            "description",
            "1997",
            "jersey",
            "urlWebsite",
            "facebook",
            "instagram",
            "twitter",
            "youtube",
            "league"
        )
    )

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


    @Test
    fun getTeamsByLeagueWhenIsLocal() {
        val leagueName = "Spanish La Liga"

        runBlocking {
            Mockito.`when`(localSource.teamsIsEmpty(leagueName)).thenReturn(false)
            Mockito.`when`(localSource.getTeamsByLeague(leagueName)).thenReturn(teams)

            val result = repository.getTeamsByLeague(leagueName)
            Mockito.verify(localSource,Mockito.times(1)).teamsIsEmpty(leagueName)
            Mockito.verify(localSource,Mockito.times(1)).getTeamsByLeague(leagueName)
            Mockito.verify(remoteSource,Mockito.times(0)).getTeamsByLeague(leagueName)

            Assert.assertEquals(teams,result)

        }


    }

    @Test
    fun getTeamsByLeagueWhenisRemote() {
        val leagueName = "Spanish La Liga"

        runBlocking {
            Mockito.`when`(localSource.teamsIsEmpty(leagueName)).thenReturn(true)
            Mockito.`when`(remoteSource.getTeamsByLeague(leagueName)).thenReturn(teams)

            val result = repository.getTeamsByLeague(leagueName)
            Mockito.verify(localSource,Mockito.times(1)).teamsIsEmpty(leagueName)
            Mockito.verify(remoteSource,Mockito.times(1)).getTeamsByLeague(leagueName)
            Mockito.verify(localSource,Mockito.times(1)).saveTeams(teams)
            Mockito.verify(localSource,Mockito.times(0)).getTeamsByLeague(leagueName)

            Assert.assertEquals(teams,result)
        }
    }

    @Test
    fun getEventsByTeameWhenIsLocal() {
        val idTeam = "133601"

        runBlocking {
            Mockito.`when`(localSource.eventsIsEmpty(idTeam)).thenReturn(false)
            Mockito.`when`(localSource.getNextEventsByTeam(idTeam)).thenReturn(events)

            val result = repository.getNextEventsByTeam(idTeam)
            Mockito.verify(localSource,Mockito.times(1)).eventsIsEmpty(idTeam)
            Mockito.verify(localSource,Mockito.times(1)).getNextEventsByTeam(idTeam)
            Mockito.verify(remoteSource,Mockito.times(0)).getNextEventsByTeam(idTeam)

            Assert.assertEquals(events,result)

        }


    }

    @Test
    fun getEventsByTeameWhenisRemote() {
        val idTeam = "133601"

        runBlocking {
            Mockito.`when`(localSource.eventsIsEmpty(idTeam)).thenReturn(true)
            Mockito.`when`(remoteSource.getNextEventsByTeam(idTeam)).thenReturn(events)

            val result = repository.getNextEventsByTeam(idTeam)
            Mockito.verify(localSource,Mockito.times(1)).eventsIsEmpty(idTeam)
            Mockito.verify(remoteSource,Mockito.times(1)).getNextEventsByTeam(idTeam)
            Mockito.verify(localSource,Mockito.times(1)).saveEvents(events)
            Mockito.verify(localSource,Mockito.times(0)).getNextEventsByTeam(idTeam)

            Assert.assertEquals(events,result)
        }
    }

}