package com.vemiranda.damkeep.retrofit

import com.vemiranda.damkeep.retrofit.request.LoginRequest
import com.vemiranda.damkeep.retrofit.request.NotaEditRequest
import com.vemiranda.damkeep.retrofit.request.RegisterRequest
import com.vemiranda.damkeep.retrofit.responses.*
import retrofit2.Call
import retrofit2.http.*

interface IDamKeepService {

    @POST("auth/login")
    fun postLogin(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("user/")
    fun postRegister(@Body usuario: RegisterRequest): Call<RegisterResponse>

    @GET("notas/usuario/")
    fun getListaNotas(): Call <NotasListResponse>

    @POST("notas/nueva")
    fun postNota(@Body noteRequest: NotaEditRequest): Call<NotaDetailResponse>

    @GET("notas/{id}")
    fun getNota(@Path("id") id: String): Call<NotaDetailResponse>

    @DELETE("notas/{id}")
    fun deleteNota(@Path("id") id: String): Call<Void>

    @PUT("notas/{id}")
    fun putNota(@Path("id") id: String, @Body request: NotaEditRequest): Call<NotaDetailResponse>

}