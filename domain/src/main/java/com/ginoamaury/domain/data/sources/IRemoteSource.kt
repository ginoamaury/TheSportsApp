package com.ginoamaury.domain.data.sources

import com.ginoamaury.domain.model.Event
import com.ginoamaury.domain.model.Team

interface IRemoteSource {
    suspend fun getTeamsByLeague(leagueName: String): List<Team>
    suspend fun getNextEventsByTeam(idTeam: String): List<Event>
}