package com.ginoamaury.thesportsapp.view.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ginoamaury.thesportsapp.view.fragments.TeamsFragment

class ViewPagerAdapterDashboard (fragment: FragmentManager ,lifecycle: Lifecycle) : FragmentStateAdapter(fragment,lifecycle) {

    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment {
        return  TeamsFragment()
    }


}