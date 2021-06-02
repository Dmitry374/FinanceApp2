package com.dima.financeapp.network

import com.dima.financeapp.model.net.BillResponse
import com.dima.financeapp.model.net.RecordResponse
import com.dima.financeapp.model.net.UserResponse
import com.dima.financeapp.network.request.AddBillRequestItem
import com.dima.financeapp.network.request.AddRecordRequestItem
import com.dima.financeapp.network.request.AuthorisationRequestItem
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user/login")
    fun loginUser(@Body authorisationRequestItem: AuthorisationRequestItem): Single<UserResponse>

    @POST("user/register")
    fun registerUser(@Body authorisationRequestItem: AuthorisationRequestItem): Single<UserResponse>

    @POST("bill/add")
    fun addBill(@Body addBillRequestItem: AddBillRequestItem): Single<BillResponse>

    @POST("record/add")
    fun addRecord(@Body addRecordRequestItem: AddRecordRequestItem): Single<RecordResponse>
}