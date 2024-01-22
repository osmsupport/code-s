package com.example.cricketapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cricketapp.R
import com.example.cricketapp.databinding.ItemPlayersBinding
import com.example.cricketapp.databinding.ItemTeamBinding
import com.example.cricketapp.model.Bowler
import com.example.cricketapp.model.Player
import com.example.cricketapp.model.Team
import com.squareup.picasso.Picasso

class PlayerAdapter(
    var mList: List<Player>, val itemClickListener: (Player) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemPlayersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPlayersBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(mList[position]) {
                binding.tvName.text = name
                binding.tvRuns.text = binding.root.context.getString(R.string.runs, runs,balls)
                binding.tvSix.text = binding.root.context.getString(R.string.six_four, fours,sixes)
                if (dismissal.fielder == null){
                    binding.tvDismissType.text =
                        binding.root.context.getString(R.string.dismissal_1, dismissal.type, dismissal.bowler)
                }else {
                    binding.tvDismissType.text =
                        binding.root.context.getString(
                            R.string.dismissal_2,
                            dismissal.type,
                            dismissal.fielder,
                            dismissal.bowler
                        )
                }
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
