package com.ginoamaury.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val id: String,
    val name: String,
    val date: String,
    val img: String,
    val idTeam: String
): Parcelable
