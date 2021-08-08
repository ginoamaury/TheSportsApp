package com.ginoamaury.domain.data.repositories

import com.ginoamaury.domain.data.sources.ILocalSource
import com.ginoamaury.domain.data.sources.IRemoteSource
import com.ginoamaury.domain.model.Event
import com.ginoamaury.domain.model.Team

class TeamRepository (
    private val localSource : ILocalSource,
    private val remoteSource : IRemoteSource
        ) {

    suspend fun getTeamsByLeague(leagueName: String): List<Team> {
        val isEmpty = localSource.teamsIsEmpty(leagueName)
        if(isEmpty){
            val teams = remoteSource.getTeamsByLeague(leagueName)
            localSource.saveTeams(teams)
            return teams
        }else{
            return localSource.getTeamsByLeague(leagueName)
        }
    }
    suspend fun getNextEventsByTeam(idTeam: String): List<Event>{
        val isEmpty = localSource.eventsIsEmpty(idTeam)
        if(isEmpty){
            val events = remoteSource.getNextEventsByTeam(idTeam)
            localSource.saveEvents(events)
            return events
        }else{
            return localSource.getNextEventsByTeam(idTeam)
        }
    }

}