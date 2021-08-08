package com.ginoamaury.thesportsapp.view.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ginoamaury.domain.model.Team
import com.ginoamaury.thesportsapp.view.fragments.ShowEventsFragment
import com.ginoamaury.thesportsapp.view.fragments.ShowInfoFragment

class ViewPagerAdapterTeam(fragment: FragmentManager, lifecycle: Lifecycle, private val team: Team) : FragmentStateAdapter(
    fragment,
    lifecycle
) {

    private val TEAMARG = "team"

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> {
                val fragment = ShowInfoFragment()
                val args = Bundle()
                args.putParcelable(TEAMARG, team)
                fragment.setArguments(args)
                return fragment
            }
            1 -> {
                val fragment = ShowEventsFragment()
                val args = Bundle()
                args.putParcelable(TEAMARG, team)
                fragment.setArguments(args)
                return fragment
            }
            else -> {
                val fragment = ShowInfoFragment()
                val args = Bundle()
                args.putParcelable(TEAMARG, team)
                fragment.setArguments(args)
                return fragment
            }
        }
    }


}