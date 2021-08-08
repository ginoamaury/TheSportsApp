package com.ginoamaury.thesportsapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ginoamaury.domain.model.Team
import com.ginoamaury.thesportsapp.R
import com.ginoamaury.thesportsapp.databinding.ItemTeamBinding
import com.ginoamaury.thesportsapp.view.fragments.IOnClickItemList

class TeamAdapter constructor(
    private val onClick: IOnClickItemList,
    private val context: Context
) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    var teams: List<Team> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = teams[position]
        holder.bind(team,context)
        holder.itemView.setOnClickListener {
            onClick.onClickItem(team)
        }
    }

    override fun getItemCount():  Int = teams.size


    class ViewHolder (private val binding : ItemTeamBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(team : Team, context: Context){
            binding.titleTeam.text = team.name
            glideImage(team.badge,context)
        }

        private fun glideImage(url: String, context: Context) {
            Glide.with(context).load(url)
                .thumbnail(0.25f)
                .error(R.drawable.ic_no_photo)
                .fallback(R.drawable.ic_no_photo)
                .into(binding.showImage)
        }

    }



}