package com.ginoamaury.thesportsapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ginoamaury.domain.model.Team
import com.ginoamaury.thesportsapp.R
import com.ginoamaury.thesportsapp.databinding.FragmentShowEventsBinding
import com.ginoamaury.thesportsapp.databinding.FragmentShowInfoBinding
import com.ginoamaury.thesportsapp.view.adapters.EventAdapter
import com.ginoamaury.thesportsapp.view.adapters.TeamAdapter
import com.ginoamaury.thesportsapp.viewmodel.EventsViewModel
import com.ginoamaury.thesportsapp.viewmodel.EventsViewState
import com.ginoamaury.thesportsapp.viewmodel.TeamsViewModel
import com.ginoamaury.thesportsapp.viewmodel.TeamsViewState
import dagger.hilt.android.AndroidEntryPoint

private const val TEAMARG = "team"

@AndroidEntryPoint
class ShowEventsFragment : Fragment(), IOnClickItemList {

    private var team: Team? = null

    private lateinit var binding: FragmentShowEventsBinding

    private val viewModel: EventsViewModel by activityViewModels()

    private lateinit var adapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            team = it.getParcelable(TEAMARG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowEventsBinding.inflate(inflater, container, false)
        val view = binding.root
        val actualTeam = team
        if(actualTeam != null){
            adapter = EventAdapter(this, requireContext())
            binding.recyclerEvents.adapter = adapter
            viewModel.getEvents(actualTeam.id).observe(requireActivity(), Observer(::updateUi))
            viewModel.listEvent()
        }

        return view
    }

    private fun updateUi(eventsViewState: EventsViewState) {
        //progress.visibility = if (teamsViewState is TeamsViewState.Loading) View.VISIBLE else View.GONE
        when(eventsViewState){
            is EventsViewState.Success -> {
                adapter.events = eventsViewState.events
                adapter.notifyDataSetChanged()
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShowEventsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClickItem(team: Team) {
        TODO("Not yet implemented")
    }
}