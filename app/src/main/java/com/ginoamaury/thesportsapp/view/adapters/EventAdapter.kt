package com.ginoamaury.thesportsapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ginoamaury.domain.model.Event
import com.ginoamaury.domain.model.Team
import com.ginoamaury.thesportsapp.R
import com.ginoamaury.thesportsapp.databinding.ItemEventBinding
import com.ginoamaury.thesportsapp.databinding.ItemTeamBinding
import com.ginoamaury.thesportsapp.view.fragments.IOnClickItemList

class EventAdapter constructor(
    private val onClick: IOnClickItemList,
    private val context: Context
) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    var events: List<Event> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event,context)
    }

    override fun getItemCount(): Int = events.size


    class ViewHolder (private val binding : ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event : Event, context: Context){
            binding.titleEvent.text = event.name
            binding.date.text = event.date
            glideImage(event.img,context)
        }

        private fun glideImage(url: String, context: Context) {
            Glide.with(context).load(url)
                .thumbnail(0.25f)
                .error(R.drawable.ic_no_photo)
                .fallback(R.drawable.ic_no_photo)
                .into(binding.eventImage)
        }

    }


}