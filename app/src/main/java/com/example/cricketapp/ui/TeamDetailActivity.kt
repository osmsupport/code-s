package com.example.cricketapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cricketapp.adapter.BowlerAdapter
import com.example.cricketapp.adapter.PlayerAdapter
import com.example.cricketapp.databinding.ActivityTeamDetailBinding
import com.example.cricketapp.model.Bowler
import com.example.cricketapp.model.Player
import com.example.cricketapp.model.Team
import com.example.cricketapp.utils.ARG_DATA
import com.google.gson.Gson

class TeamDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        intent.getStringExtra(ARG_DATA).let {
            val team = Gson().fromJson(it, Team::class.java)
            listPlayerData(team.players)
            listBowlerData(team.bowlers)
        }


    }

    private fun listPlayerData(players: List<Player>) {
        binding.rvPlayers.layoutManager = LinearLayoutManager(this)
        val adapter = PlayerAdapter(players) {
//            showBottomSheet(it)
        }
        binding.rvPlayers.adapter = adapter
    }
    private fun listBowlerData(players: List<Bowler>) {
        binding.rvBowlers.layoutManager = LinearLayoutManager(this)
        val adapter = BowlerAdapter(players) {
        }
        binding.rvBowlers.adapter = adapter
    }
}