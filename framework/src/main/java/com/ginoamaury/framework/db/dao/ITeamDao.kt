package com.ginoamaury.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ginoamaury.framework.db.entities.EventEntity
import com.ginoamaury.framework.db.entities.TeamEntity

@Dao
interface ITeamDao {
    @Query("SELECT * FROM TeamEntity")
    fun getAll(): List<TeamEntity>

    @Query("SELECT * FROM TeamEntity WHERE leagueName = :leagueName")
    fun getAllByLeague(leagueName: String): List<TeamEntity>

    @Query("SELECT COUNT(id) FROM TeamEntity WHERE leagueName = :leagueName")
    fun countTeamsByLeague(leagueName: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTeams(teams: List<TeamEntity>)
}