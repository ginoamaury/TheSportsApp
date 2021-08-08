package com.ginoamaury.thesportsapp.view.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ginoamaury.domain.model.Team
import com.ginoamaury.thesportsapp.R
import com.ginoamaury.thesportsapp.databinding.FragmentShowInfoBinding

private const val TEAMARG = "team"

class ShowInfoFragment : Fragment() {


    private var team: Team? = null

    private lateinit var binding: FragmentShowInfoBinding

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
        binding = FragmentShowInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actualTeam = team
        team?.let {
            with(binding) {
                if (it.name.isNotBlank()) titleTeam.text = it.name
                if (it.description.isNotBlank()) contentSummary.text = it.description
                if (it.foundationYear.isNotBlank()) {
                    chip.visibility = View.VISIBLE
                    val formedYear = "Founded in " + it.foundationYear
                    chip.text = formedYear
                }
                if (it.website.isNotBlank()) {
                    chipWebsite.visibility = View.VISIBLE
                    val websiteUrl = it.website
                    chipWebsite.setOnClickListener(View.OnClickListener { openWebURL(websiteUrl) })
                }
                if (it.facebook.isNotBlank()) {
                    chipFacebook.visibility = View.VISIBLE
                    val websiteUrl = it.facebook
                    chipFacebook.setOnClickListener(View.OnClickListener { openWebURL(websiteUrl) })
                }
                if (it.twitter.isNotBlank()) {
                    chipTwitter.visibility = View.VISIBLE
                    val websiteUrl = it.twitter
                    chipTwitter.setOnClickListener(View.OnClickListener { openWebURL(websiteUrl) })
                }
                if (it.instagram.isNotBlank()) {
                    chipInstagram.visibility = View.VISIBLE
                    val websiteUrl = it.instagram
                    chipInstagram.setOnClickListener(View.OnClickListener { openWebURL(websiteUrl) })
                }
                if (it.youtube.isNotBlank()) {
                    chipYoutube.visibility = View.VISIBLE
                    val websiteUrl = it.youtube
                    chipYoutube.setOnClickListener(View.OnClickListener { openWebURL(websiteUrl) })
                }
                val jersey = it.jersey
                if (jersey != null) glideImage(jersey, imgJersey, view)
                val stadium = it.stadium
                if (stadium != null) glideImage(stadium, imgStadium, view)
            }
        }

    }

    fun openWebURL(inURL: String) {
        val URL = "http://$inURL"
        val browse = Intent(Intent.ACTION_VIEW, Uri.parse(URL))
        startActivity(browse)
    }


    private fun glideImage(url: String, imgView: ImageView, view: View) {
        Glide.with(view).load(url)
            .thumbnail(0.25f)
            .error(R.drawable.ic_no_photo)
            .fallback(R.drawable.ic_no_photo)
            .into(imgView)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            ShowInfoFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}