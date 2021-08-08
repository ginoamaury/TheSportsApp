package com.ginoamaury.framework.network.model

import android.os.Parcelable
import com.ginoamaury.domain.model.Event
import com.ginoamaury.domain.model.Team
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventsNetwork(
    @SerializedName("results") val events: List<EventNetwork>,
) : Parcelable {

    fun map(idTeam : String): List<Event> {
        return events.map {
            it.map(idTeam)
        }

    }
}