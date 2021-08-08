package com.ginoamaury.thesportsapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ginoamaury.domain.model.Team
import com.ginoamaury.domain.usecases.GetEventsByTeam
import com.ginoamaury.domain.usecases.GetTeamsByLeague
import com.ginoamaury.thesportsapp.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
@Config(manifest = Config.NONE)
class TeamsViewModelTest {

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

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var getTeamsByLeague: GetTeamsByLeague

    lateinit var teamsViewModel: TeamsViewModel

    @Mock
    lateinit var observer: Observer<TeamsViewState>


    @Before
    fun start() {
        teamsViewModel = TeamsViewModel(getTeamsByLeague, coroutineTestRule.testDispatcher)
    }


    @Test
    fun listTeams() {
        val leagueName = "Spanish La Liga"
        runBlocking {
            Mockito.`when`(getTeamsByLeague.invoke(leagueName)).thenReturn(teams)
            teamsViewModel.getTeams(leagueName).observeForever(observer)
            teamsViewModel.listTeams()
            Mockito.verify(getTeamsByLeague, Mockito.times(1)).invoke(leagueName)
            Mockito.verify(observer, Mockito.times(1)).onChanged(TeamsViewState.Loading)
            Mockito.verify(observer, Mockito.times(1)).onChanged(TeamsViewState.Success(teams))
            teamsViewModel.getTeams(leagueName).removeObserver(observer)
        }
    }

}