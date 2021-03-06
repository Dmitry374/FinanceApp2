package com.dima.financeapp.network

import com.dima.financeapp.model.net.BillResponse
import com.dima.financeapp.model.net.RecordResponse
import com.dima.financeapp.model.net.UserResponse
import com.dima.financeapp.network.request.AddBillRequestItem
import com.dima.financeapp.network.request.AddRecordRequestItem
import com.dima.financeapp.network.request.AuthorisationRequestItem
import com.dima.financeapp.network.request.RegistrationRequestItem
import com.dima.financeapp.network.request.UserEditRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("user/login")
    fun loginUser(@Body authorisationRequestItem: AuthorisationRequestItem): Single<UserResponse>

    @FormUrlEncoded
    @POST("user/sign_in")
    fun signInUser(@Field("email") email: String): Single<UserResponse>

    @POST("user/register")
    fun registerUser(@Body registrationRequestItem: RegistrationRequestItem): Single<UserResponse>

    @PUT("user/{id}")
    fun editUser(@Path("id") id: Long, @Body userEditRequest: UserEditRequest): Single<UserResponse>

    @POST("bill/add")
    fun addBill(@Body addBillRequestItem: AddBillRequestItem): Single<BillResponse>

    @POST("record/add")
    fun addRecord(@Body addRecordRequestItem: AddRecordRequestItem): Single<RecordResponse>
}