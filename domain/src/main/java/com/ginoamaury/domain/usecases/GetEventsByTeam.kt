package com.ginoamaury.domain.usecases

import com.ginoamaury.domain.data.repositories.TeamRepository

class GetEventsByTeam (private val teamRepository: TeamRepository) {
    suspend operator fun invoke (
        idTeam: String
    ) = teamRepository.getNextEventsByTeam(idTeam)
}