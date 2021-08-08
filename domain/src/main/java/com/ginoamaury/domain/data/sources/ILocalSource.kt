package com.ginoamaury.domain.data.sources

import com.ginoamaury.domain.model.Event
import com.ginoamaury.domain.model.Team

interface ILocalSource {
    suspend fun teamsIsEmpty(leagueName: String): Boolean
    suspend fun eventsIsEmpty(idTeam: String): Boolean
    suspend fun saveTeams(teams: List<Team>)
    suspend fun saveEvents(events: List<Event>)
    suspend fun getTeamsByLeague(leagueName: String): List<Team>
    suspend fun getNextEventsByTeam(idTeam: String): List<Event>
}