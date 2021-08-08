package com.ginoamaury.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class League(
    val name: String,
    val teams: List<Team>
): Parcelable
