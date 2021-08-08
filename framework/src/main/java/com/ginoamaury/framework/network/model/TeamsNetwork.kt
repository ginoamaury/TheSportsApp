package com.ginoamaury.framework.network.model

import android.os.Parcelable
import com.ginoamaury.domain.model.Team
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class TeamsNetwork(
    @SerializedName("teams") val teams: List<TeamNetwork>,
) : Parcelable {

    fun map(leagueName : String): List<Team> {
        return teams.map {
            it.map(leagueName)
        }

    }



}