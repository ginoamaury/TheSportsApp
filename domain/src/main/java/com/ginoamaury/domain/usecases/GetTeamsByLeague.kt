package com.ginoamaury.domain.usecases

import com.ginoamaury.domain.data.repositories.TeamRepository

class GetTeamsByLeague (private val teamRepository: TeamRepository) {
    suspend operator fun invoke (
        leagueName: String
    ) = teamRepository.getTeamsByLeague(leagueName)
}