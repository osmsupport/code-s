package com.example.cricketapp

import com.example.cricketapp.network.api.ApiServices
import javax.inject.Inject

class MainApiRepository @Inject constructor(private val apiService: ApiServices) {

    fun getMatchDetails() = apiService.getMatchDetails()
}