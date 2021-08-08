package com.ginoamaury.thesportsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ginoamaury.domain.usecases.GetEventsByTeam
import com.ginoamaury.domain.usecases.GetTeamsByLeague
import com.ginoamaury.framework.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor (
    private val getEventsByTeam: GetEventsByTeam,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private lateinit var idTeam: String

    private val model: MutableLiveData<EventsViewState> = MutableLiveData()


    fun getEvents(idTeam: String): LiveData<EventsViewState> {
        this.idTeam = idTeam
        return model
    }

    fun listEvent() {
        viewModelScope.launch(ioDispatcher) {
            model.postValue(EventsViewState.Loading)
            val list = getEventsByTeam.invoke(idTeam)
            model.postValue(EventsViewState.Success(list))
        }
    }

}
