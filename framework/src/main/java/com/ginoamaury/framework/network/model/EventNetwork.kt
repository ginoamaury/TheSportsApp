package com.ginoamaury.framework.network.model

import android.os.Parcelable
import com.ginoamaury.domain.model.Event
import com.ginoamaury.domain.model.Team
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventNetwork(
    @SerializedName("idEvent") val id: String,
    @SerializedName("strEvent") val name: String,
    @SerializedName("strTimestamp") val date: String,
    @SerializedName("strThumb") val img: String
) : Parcelable {

    fun map(idTeam: String): Event {
        return Event(
            id,
            name,
            date,
            img,
            idTeam
        )
    }
}