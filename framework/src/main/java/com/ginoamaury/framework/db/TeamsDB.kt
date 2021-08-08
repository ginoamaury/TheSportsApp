package com.ginoamaury.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ginoamaury.framework.db.dao.IEventDao
import com.ginoamaury.framework.db.dao.ITeamDao
import com.ginoamaury.framework.db.entities.EventEntity
import com.ginoamaury.framework.db.entities.TeamEntity

@Database(entities = [TeamEntity::class, EventEntity::class],version = 1 , exportSchema = false)
abstract class TeamsDB : RoomDatabase() {
    abstract fun teamDao() : ITeamDao
    abstract fun eventDao() : IEventDao
}