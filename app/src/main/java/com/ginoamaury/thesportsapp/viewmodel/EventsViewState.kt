package com.ginoamaury.thesportsapp.viewmodel

import com.ginoamaury.domain.model.Event

sealed class EventsViewState{
    object Loading : EventsViewState()
    data class Success(val events: List<Event>) : EventsViewState()
}
