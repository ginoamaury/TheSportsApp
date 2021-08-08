package com.ginoamaury.domain.usecases

import com.ginoamaury.domain.data.repositories.TeamRepository
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
class GetTeamsByLeagueTest {

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

    @Mock
    lateinit var teamRepository: TeamRepository

    @InjectMocks
    lateinit var getTeamsByLeague: GetTeamsByLeague

    @Test
    fun invokeTest(){
        val leagueName = "Spanish La Liga"
        runBlocking {
            Mockito.`when`(teamRepository.getTeamsByLeague(leagueName)).thenReturn(teams)
            val result = getTeamsByLeague(leagueName)
            Mockito.verify(teamRepository, Mockito.times(1)).getTeamsByLeague(leagueName)
            Assert.assertEquals(teams,result)
        }

    }

}