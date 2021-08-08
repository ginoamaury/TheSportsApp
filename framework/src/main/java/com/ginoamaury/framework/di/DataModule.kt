package com.ginoamaury.framework.di

import com.ginoamaury.domain.data.repositories.TeamRepository
import com.ginoamaury.domain.data.sources.ILocalSource
import com.ginoamaury.domain.data.sources.IRemoteSource
import com.ginoamaury.framework.db.TeamLocalSource
import com.ginoamaury.framework.db.TeamsDB
import com.ginoamaury.framework.db.dao.IEventDao
import com.ginoamaury.framework.db.dao.ITeamDao
import com.ginoamaury.framework.network.ITeamsService
import com.ginoamaury.framework.network.TeamRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesRepository(localSource: ILocalSource, remoteSource: IRemoteSource): TeamRepository =
        TeamRepository(localSource, remoteSource)


    @Provides
    fun provideRemoteSource(teamsService: ITeamsService): IRemoteSource =
        TeamRemoteSource(teamsService)

    @Provides
    fun provideLocalSource(teamDao: ITeamDao, evenDao: IEventDao): ILocalSource =
        TeamLocalSource(teamDao, evenDao)

}