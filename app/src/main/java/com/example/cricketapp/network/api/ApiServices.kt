package com.example.cricketapp.network.api

import com.example.cricketapp.model.MatchDetails
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {
	@GET("560e690a-e516-43d5-ac78-fe5ff270513d")
	fun getMatchDetails() : Call<MatchDetails>
}
