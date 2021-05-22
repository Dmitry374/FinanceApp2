package com.dima.financeapp.network

import com.dima.financeapp.model.net.UserResponse
import com.dima.financeapp.network.request.AuthorisationRequestItem
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user/login")
    fun loginUser(@Body authorisationRequestItem: AuthorisationRequestItem): Single<UserResponse>

    @POST("user/register")
    fun registerUser(@Body authorisationRequestItem: AuthorisationRequestItem): Single<UserResponse>
}