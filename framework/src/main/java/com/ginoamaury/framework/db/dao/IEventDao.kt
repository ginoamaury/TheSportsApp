package com.ginoamaury.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ginoamaury.framework.db.entities.EventEntity

@Dao
interface IEventDao {

    @Query("SELECT * FROM Evententity")
    fun getAll(): List<EventEntity>

    @Query("SELECT * FROM Evententity WHERE idTeam = :idTeam")
    fun getAllByTeam(idTeam: String): List<EventEntity>

    @Query("SELECT COUNT(id) FROM Evententity WHERE idTeam = :idTeam")
    fun countEventsByTeam(idTeam: String): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEvent(events: List<EventEntity>)

}