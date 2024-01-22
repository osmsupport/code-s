package com.example.cricketapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cricketapp.databinding.ItemTeamBinding
import com.example.cricketapp.model.Bowler
import com.example.cricketapp.model.Player
import com.example.cricketapp.model.Team
import com.squareup.picasso.Picasso

class TeamAdapter(
    var mList: List<Team>, val itemClickListener: (Team)->Unit
) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(mList[position]){
                binding.tvTeam.text = name
                binding.tvRuns.text = findTotalRuns(players)
                binding.tvOver.text = findTotalOvers(bowlers)

                binding.root.setOnClickListener {
                    itemClickListener(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    private fun findTotalRuns(players: List<Player>): String {
        var runs = 0
        for (player in players) {
            runs += player.runs
        }
        return "$runs Runs"
    }
    private fun findTotalOvers(players: List<Bowler>): String {
        var overs = 0
        for (player in players) {
            overs += player.overs
        }
        return "$overs Overs"
    }
}
