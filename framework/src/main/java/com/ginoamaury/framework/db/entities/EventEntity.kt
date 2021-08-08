package com.ginoamaury.framework.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ginoamaury.domain.model.Event
import com.ginoamaury.domain.model.Team

@Entity
data class EventEntity(
    @PrimaryKey val id: String,
    val name: String,
    val date: String,
    val img: String,
    val idTeam: String
){

    constructor(event: Event) : this(
        id = event.id,
        name = event.name,
        date = event.date,
        img = event.img,
        idTeam = event.idTeam
    )

    fun map(): Event {
        return Event(
            id,
            name,
            date,
            img,
            idTeam
        )
    }

}
