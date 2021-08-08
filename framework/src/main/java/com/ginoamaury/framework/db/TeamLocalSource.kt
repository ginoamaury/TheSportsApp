package com.ginoamaury.framework.db

import com.ginoamaury.domain.data.sources.ILocalSource
import com.ginoamaury.domain.model.Event
import com.ginoamaury.domain.model.Team
import com.ginoamaury.framework.db.dao.IEventDao
import com.ginoamaury.framework.db.dao.ITeamDao
import com.ginoamaury.framework.db.entities.EventEntity
import com.ginoamaury.framework.db.entities.TeamEntity

class TeamLocalSource(private val teamDao: ITeamDao, private val eventDao: IEventDao) :
    ILocalSource {

    override suspend fun teamsIsEmpty(leagueName: String): Boolean =
        teamDao.countTeamsByLeague(leagueName) <= 0


    override suspend fun eventsIsEmpty(idTeam: String): Boolean =
        eventDao.countEventsByTeam(idTeam) <= 0

    override suspend fun saveTeams(teams: List<Team>) {
        teamDao.insertTeams(teams.map { TeamEntity(it) })
    }

    override suspend fun saveEvents(events: List<Event>) {
        eventDao.insertEvent(events.map { EventEntity(it) })
    }

    override suspend fun getTeamsByLeague(leagueName: String): List<Team> =
        teamDao.getAllByLeague(leagueName).map { it.map() }


    override suspend fun getNextEventsByTeam(idTeam: String): List<Event> =
        eventDao.getAllByTeam(idTeam).map { it.map() }
}