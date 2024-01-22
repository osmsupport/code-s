package com.example.cricketapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cricketapp.adapter.TeamAdapter
import com.example.cricketapp.databinding.ActivityMainBinding
import com.example.cricketapp.model.Bowler
import com.example.cricketapp.model.MatchDetailsX
import com.example.cricketapp.model.Player
import com.example.cricketapp.model.Team
import com.example.cricketapp.network.BaseResponse
import com.example.cricketapp.utils.ARG_DATA
import com.example.cricketapp.viewmodel.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getMatchDetails()

        viewModel.observeMatchDetailsLiveData().observe(this, Observer {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.pbMain.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.pbMain.visibility = View.GONE
                    setData(it.data!!.matchDetails)
                }

                is BaseResponse.Error -> {
                    Toast.makeText(this, "${it.msg}", Toast.LENGTH_SHORT).show()
                    binding.pbMain.visibility = View.GONE
                }

                else -> {
                    binding.pbMain.visibility = View.GONE
                }
            }
        })

    }

    private fun setData(data: MatchDetailsX) {
        binding.apply {
            tvTeamATitle.text = data.teams[0].name
            tvTeamBTitle.text = data.teams[1].name
            tvTitle.text = findWinner(data.teams)
        }

        listData(data)
    }

    private fun findWinner(teams: List<Team>): String {
        var runsTeamA = 0
        var runsTeamB = 0
        for (playerA in teams[0].players) {
            runsTeamA += playerA.runs
        }

        for (playerB in teams[1].players) {
            runsTeamB += playerB.runs
        }

        if (runsTeamA > runsTeamB) {
            return "${teams[0].name} won by ${runsTeamA - runsTeamB} runs"
        } else {
            return "${teams[1].name} won by ${runsTeamB - runsTeamA} runs"
        }
    }

    private fun listData(data: MatchDetailsX) {
        binding.rVteam.layoutManager = LinearLayoutManager(this)
        val adapter = TeamAdapter(data.teams) {
            val intent = Intent(this, TeamDetailActivity::class.java)
            intent.putExtra(ARG_DATA, Gson().toJson(it))
            startActivity(intent)
        }
        binding.rVteam.adapter = adapter
    }
}