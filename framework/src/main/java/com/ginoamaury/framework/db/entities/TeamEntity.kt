package com.ginoamaury.framework.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ginoamaury.domain.model.Team

@Entity
data class TeamEntity(
    @PrimaryKey val id: String,
    val name: String,
    val stadium: String?,
    val badge: String,
    val description: String,
    val foundationYear: String,
    val jersey: String?,
    val website: String,
    val facebook: String,
    val instagram: String,
    val twitter: String,
    val youtube: String,
    val leagueName: String
) {
    constructor(team: Team) : this(
        id = team.id,
        name = team.name,
        stadium = team.stadium,
        badge = team.badge,
        description = team.description,
        foundationYear = team.foundationYear,
        jersey = team.jersey,
        website = team.website,
        facebook = team.facebook,
        instagram = team.instagram,
        twitter = team.twitter,
        youtube = team.youtube,
        leagueName = team.leagueName

    )

    fun map(): Team {
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
