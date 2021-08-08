package com.ginoamaury.thesportsapp.view

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.ginoamaury.domain.model.Team
import com.ginoamaury.thesportsapp.R
import com.ginoamaury.thesportsapp.databinding.ActivityTeamBinding
import com.ginoamaury.thesportsapp.view.adapters.ViewPagerAdapterTeam
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamBinding
    private lateinit var team: Team
    val args: TeamActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val bundle = args
        team = bundle.team
        setSupportActionBar(binding.toolbar)
        addToolbar()
        setImage()
        showToolbarViewPager()


    }

    private fun addToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.title = ""
        val colorNavigationIcon =  ContextCompat.getColor(this,R.color.White)
        val iconNavigation = ContextCompat.getDrawable(this, R.drawable.ic_left_arrow)
        iconNavigation?.setColorFilter(colorNavigationIcon, PorterDuff.Mode.SRC_ATOP)
        binding.toolbar.navigationIcon = iconNavigation
        onClickNavigation()
    }

    private fun onClickNavigation() {
        binding.toolbar.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })
    }

    private fun showToolbarViewPager() {
        val viewPager = binding.contentDash.pager
        val tabLayout = binding.tabs
        val titleTabs = arrayOf(
            resources.getString(R.string.viewPagerInfo),
            resources.getString(R.string.viewPagerEvents)
        )
        val pagerAdapter = ViewPagerAdapterTeam(supportFragmentManager, lifecycle, team)
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titleTabs[position]
        }.attach()
    }

    private fun setImage() {
        if (team.badge != "") {
            val urlImage: String = team.badge
            glideImage(urlImage, this)
        }
    }

    private fun glideImage(url: String, context: Context) {
        Glide.with(context).load(url)
            .thumbnail(0.25f)
            .error(R.drawable.ic_no_photo)
            .fallback(R.drawable.ic_no_photo)
            .into(binding.showImage)
    }

}