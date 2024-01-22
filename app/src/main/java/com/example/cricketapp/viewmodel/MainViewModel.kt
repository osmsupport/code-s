package com.example.cricketapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cricketapp.MainApiRepository
import com.example.cricketapp.model.MatchDetails
import com.example.cricketapp.network.BaseResponse
import com.example.cricketapp.network.api.ApiServices
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiRepository: MainApiRepository) : ViewModel() {

    private var matchDetailsLivedata: MutableLiveData<BaseResponse<MatchDetails>> = MutableLiveData()

    fun getMatchDetails() {

        matchDetailsLivedata.value = BaseResponse.Loading()


        apiRepository.getMatchDetails().enqueue(object :
            Callback<MatchDetails> {
            override fun onResponse(
                call: Call<MatchDetails>,
                response: Response<MatchDetails>
            ) {
                if (response.body() != null) {
                    matchDetailsLivedata.value = BaseResponse.Success(response.body())

                } else {
                    matchDetailsLivedata.value = BaseResponse.Error(response.message())
                }
            }

            override fun onFailure(call: Call<MatchDetails>, t: Throwable) {
                matchDetailsLivedata.value = BaseResponse.Error(t.message)
            }
        })
    }

    fun observeMatchDetailsLiveData(): LiveData<BaseResponse<MatchDetails>> {
        return matchDetailsLivedata
    }


} 
