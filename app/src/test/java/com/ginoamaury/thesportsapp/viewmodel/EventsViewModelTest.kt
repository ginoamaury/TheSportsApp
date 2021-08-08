package com.ginoamaury.thesportsapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ginoamaury.domain.model.Event
import com.ginoamaury.domain.usecases.GetEventsByTeam
import com.ginoamaury.thesportsapp.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
@Config(manifest = Config.NONE)
class EventsViewModelTest {
    private val events = listOf(
        Event(
            "1",
            "event N1",
            "date",
            "urlImg",
            "1"
        ),
        Event(
            "2",
            "event N2",
            "date",
            "urlImg",
            "1"
        )
    )

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var getEventsByTeam: GetEventsByTeam

    lateinit var eventsViewModel: EventsViewModel

    @Mock
    lateinit var observer: Observer<EventsViewState>


    @Before
    fun start() {
        eventsViewModel = EventsViewModel(getEventsByTeam, coroutineTestRule.testDispatcher)
    }

    @Test
    fun listEvents() {
        val idTeam = "2"
        runBlocking {
            Mockito.`when`(getEventsByTeam.invoke(idTeam)).thenReturn(events)
            eventsViewModel.getEvents(idTeam).observeForever(observer)
            eventsViewModel.listEvent()
            Mockito.verify(getEventsByTeam, Mockito.times(1)).invoke(idTeam)
            Mockito.verify(observer, Mockito.times(1)).onChanged(EventsViewState.Loading)
            Mockito.verify(observer, Mockito.times(1)).onChanged(EventsViewState.Success(events))
            eventsViewModel.getEvents(idTeam).removeObserver(observer)
        }
    }


}