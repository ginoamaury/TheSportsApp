package com.ginoamaury.framework.network.model

import android.os.Parcelable
import androidx.annotation.Nullable
import com.ginoamaury.domain.model.Team
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamNetwork(
    @SerializedName("idTeam") val id: String,
    @SerializedName("strTeam") val name: String,
    @Nullable @SerializedName("strStadiumThumb") val stadium: String?,
    @SerializedName("strTeamBadge") val badge: String,
    @SerializedName("strDescriptionEN") val description: String,
    @SerializedName("intFormedYear") val foundationYear: String,
    @Nullable @SerializedName("strTeamJersey") val jersey: String?,
    @SerializedName("strWebsite") val website: String,
    @SerializedName("strFacebook") val facebook: String,
    @SerializedName("strInstagram") val instagram: String,
    @SerializedName("strTwitter") val twitter: String,
    @SerializedName("strYoutube") val youtube: String
) : Parcelable {

    fun map(leagueName : String): Team {
        return Team(
            id,
            name,
            stadium,
            badge,
            description,
            foundationYear,
            jersey,
            website,
            facebook,
            instagram,
            twitter,
            youtube,
            leagueName
        )
    }
}