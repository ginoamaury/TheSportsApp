package com.ginoamaury.thesportsapp.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ginoamaury.domain.model.Team
import com.ginoamaury.thesportsapp.R
import com.ginoamaury.thesportsapp.databinding.FragmentTeamsBinding
import com.ginoamaury.thesportsapp.view.TeamActivity
import com.ginoamaury.thesportsapp.view.adapters.TeamAdapter
import com.ginoamaury.thesportsapp.viewmodel.TeamsViewModel
import com.ginoamaury.thesportsapp.viewmodel.TeamsViewState
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : Fragment(), IOnClickItemList {

    private lateinit var binding: FragmentTeamsBinding
    private lateinit var adapter: TeamAdapter



    private val viewModel:  TeamsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamsBinding.inflate(inflater, container, false)
        val view = binding.root
        val leagueName  = "Spanish La Liga"
        adapter = TeamAdapter(this, requireContext())
        binding.recyclerTeams.adapter = adapter
        viewModel.getTeams(leagueName).observe(requireActivity(), Observer(::updateUi))
        viewModel.listTeams()
        showFragmentsNavigation()
        return view
    }

    private fun updateUi(teamsViewState: TeamsViewState) {
        //progress.visibility = if (teamsViewState is TeamsViewState.Loading) View.VISIBLE else View.GONE
        when(teamsViewState){
            is TeamsViewState.Success -> {
                adapter.teams = teamsViewState.teams
                adapter.notifyDataSetChanged()
            }

        }
    }

    private fun showFragmentsNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_spanish -> {
                    //showPreviewLoading()
                    val leagueName  = "Spanish La Liga"
                    viewModel.getTeams(leagueName).observe(requireActivity(), Observer(::updateUi))
                    viewModel.listTeams()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_premier_league -> {
                    //showPreviewLoading()
                    val leagueName  = "English League Championship"
                    viewModel.getTeams(leagueName).observe(requireActivity(), Observer(::updateUi))
                    viewModel.listTeams()
                    return@setOnItemSelectedListener true
                }
                R.id.menu_italian_seriea -> {
                    //showPreviewLoading()
                    val leagueName  = "Italian Serie A"
                    viewModel.getTeams(leagueName).observe(requireActivity(), Observer(::updateUi))
                    viewModel.listTeams()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onClickItem(team: Team) {
        //val bundle = bundleOf("team" to team)
        val action = TeamsFragmentDirections.actionTeamsFragmentToTeamActivity(team)
        findNavController().navigate(action)
    }

}