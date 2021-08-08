package com.ginoamaury.framework.di

import android.app.Application
import androidx.room.Room
import com.ginoamaury.framework.db.TeamsDB
import com.ginoamaury.framework.db.dao.IEventDao
import com.ginoamaury.framework.db.dao.ITeamDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun databaseProvider(app: Application): TeamsDB = Room.databaseBuilder(
        app,
        TeamsDB::class.java,
        "sports-db"
    ).build()

    @Provides
    fun providesTeamDao(dataBase : TeamsDB) : ITeamDao = dataBase.teamDao()

    @Provides
    fun providesEventDao(dataBase: TeamsDB) : IEventDao = dataBase.eventDao()

}