package com.ginoamaury.thesportsapp.module

import com.ginoamaury.domain.data.repositories.TeamRepository
import com.ginoamaury.domain.usecases.GetTeamsByLeague
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(FragmentComponent::class)
class TeamsModule {
    @Provides
    fun getTeamsByLeague(teamsRepository: TeamRepository) = GetTeamsByLeague(teamsRepository)
}