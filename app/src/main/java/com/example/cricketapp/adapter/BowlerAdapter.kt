package com.example.cricketapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cricketapp.R
import com.example.cricketapp.databinding.ItemBowlersBinding
import com.example.cricketapp.databinding.ItemPlayersBinding
import com.example.cricketapp.databinding.ItemTeamBinding
import com.example.cricketapp.model.Bowler
import com.example.cricketapp.model.Player
import com.example.cricketapp.model.Team
import com.squareup.picasso.Picasso

class BowlerAdapter(
    var mList: List<Bowler>, val itemClickListener: (Bowler)->Unit
) : RecyclerView.Adapter<BowlerAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemBowlersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBowlersBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(mList[position]){
                binding.tvName.text = name
                binding.tvOvers.text = binding.root.context.getString(R.string.overs, overs)
                binding.tvRuns.text = binding.root.context.getString(R.string.runs_conceded, runsConceded)
                binding.tvWickets.text = binding.root.context.getString(R.string.wickets, wickets)

                binding.root.setOnClickListener {
                    itemClickListener(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}
