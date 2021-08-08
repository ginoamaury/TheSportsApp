package com.ginoamaury.thesportsapp.viewmodel


import com.ginoamaury.domain.model.Team

sealed class TeamsViewState  {
    object Loading : TeamsViewState()
    data class Success(val teams: List<Team>) : TeamsViewState()
}