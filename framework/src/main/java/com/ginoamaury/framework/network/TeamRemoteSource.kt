package com.ginoamaury.framework.network

import com.ginoamaury.domain.data.sources.IRemoteSource
import com.ginoamaury.domain.model.Event
import com.ginoamaury.domain.model.Team

class TeamRemoteSource(private val teamsService: ITeamsService) : IRemoteSource {
    override suspend fun getTeamsByLeague(leagueName: String): List<Team> =
        teamsService.getTeamsByLeague(leagueName).map(leagueName)

    override suspend fun getNextEventsByTeam(idTeam: String): List<Event> =
        teamsService.getNextEventsByTeam(idTeam).map(idTeam)
}