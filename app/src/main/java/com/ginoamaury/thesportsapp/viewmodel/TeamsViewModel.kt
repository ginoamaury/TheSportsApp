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
class TeamsViewModel @Inject constructor (
    private val getTeamsByLeague: GetTeamsByLeague,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private lateinit var leagueName: String
    private lateinit var idTeam: String

    private val model: MutableLiveData<TeamsViewState> = MutableLiveData()

    fun getTeams(leagueName: String): LiveData<TeamsViewState> {
        this.leagueName = leagueName
        return model
    }


    fun listTeams() {
        viewModelScope.launch(ioDispatcher) {
            model.postValue(TeamsViewState.Loading)
            val list = getTeamsByLeague.invoke(leagueName)
            model.postValue(TeamsViewState.Success(list))
        }
    }



}