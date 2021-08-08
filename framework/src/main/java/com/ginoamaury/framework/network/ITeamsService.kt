package com.ginoamaury.framework.network

import com.ginoamaury.framework.network.model.EventsNetwork
import com.ginoamaury.framework.network.model.TeamsNetwork
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ITeamsService {
    @GET(QUERY_ALLTEAMS)
    suspend fun getTeamsByLeague(
        @Query("l") query: String
    ): TeamsNetwork


    @GET(QUERY_EVENTS)
    suspend fun getNextEventsByTeam(
        @Query("id") query: String
    ): EventsNetwork
}

private const val QUERY_ALLTEAMS = "search_all_teams.php"
private const val QUERY_EVENTS = "eventslast.php"