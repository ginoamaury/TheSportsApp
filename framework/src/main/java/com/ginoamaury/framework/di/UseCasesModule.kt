package com.ginoamaury.framework.di

import com.ginoamaury.domain.data.repositories.TeamRepository
import com.ginoamaury.domain.usecases.GetEventsByTeam
import com.ginoamaury.domain.usecases.GetTeamsByLeague
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    fun getTeamsByLeague(teamRepository: TeamRepository) : GetTeamsByLeague = GetTeamsByLeague(teamRepository)

    @Provides
    fun getEventsByTeam (teamRepository: TeamRepository) : GetEventsByTeam = GetEventsByTeam(teamRepository)

}