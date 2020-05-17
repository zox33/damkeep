package com.vemiranda.damkeep.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.santiagorodriguezalberto.damkeepapp.model.Nota
import com.santiagorodriguezalberto.damkeepapp.model.User
import com.vemiranda.damkeep.common.Constants
import com.vemiranda.damkeep.common.MyApp
import com.vemiranda.damkeep.common.SharedPreferencesManager
import com.vemiranda.damkeep.retrofit.IDamKeepService
import com.vemiranda.damkeep.retrofit.request.LoginRequest
import com.vemiranda.damkeep.retrofit.request.NotaEditRequest
import com.vemiranda.damkeep.retrofit.request.RegisterRequest
import com.vemiranda.damkeep.retrofit.responses.LoginResponse
import com.vemiranda.damkeep.retrofit.responses.NotaDetailResponse
import com.vemiranda.damkeep.retrofit.responses.NotasListResponse
import com.vemiranda.damkeep.retrofit.responses.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DamKeepRepository  @Inject constructor(var damKeepService:IDamKeepService) {

    var userLogin: MutableLiveData<LoginResponse> = MutableLiveData()
    var userRegister: MutableLiveData<RegisterResponse> = MutableLiveData()
    var notas: MutableLiveData<NotasListResponse> = MutableLiveData()
    var nota: MutableLiveData<NotaDetailResponse> = MutableLiveData()


    fun login(loginRequest: LoginRequest): MutableLiveData<LoginResponse> {
        val call: Call<LoginResponse> = damKeepService.postLogin(loginRequest)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (!response.isSuccessful) {
                    Log.e("RequestError", response.message())
                    Toast.makeText(MyApp.instace, "", Toast.LENGTH_SHORT).show()
                } else {
                    userLogin.value = response.body()
                    SharedPreferencesManager().setStringValue(Constants.TOKEN,response.body()!!.token)
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(MyApp.instace, "Error. Can't connect to server", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        return userLogin
    }

    fun register(registerRequest: RegisterRequest): MutableLiveData<RegisterResponse> {
        val call: Call<RegisterResponse> = damKeepService.postRegister(registerRequest)
        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (!response.isSuccessful) {
                    Log.e("RequestError", response.message())
                    Toast.makeText(MyApp.instace, "Error en el registro", Toast.LENGTH_SHORT).show()
                } else {
                    userRegister.value = response.body()
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(MyApp.instace, "Error. Can't connect to server", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        return userRegister
    }

    fun getNotasUsuario(): MutableLiveData<NotasListResponse> {
        val call: Call<NotasListResponse> = damKeepService.getListaNotas()
        call.enqueue(object : Callback<NotasListResponse> {
            override fun onResponse(call: Call<NotasListResponse>, response: Response<NotasListResponse>) {
                if (!response.isSuccessful) {
                    Log.e("RequestError", response.message())
                    Toast.makeText(MyApp.instace, "Error al mostrar lista", Toast.LENGTH_SHORT).show()
                } else {
                    notas.value = response.body()
                }
            }
            override fun onFailure(call: Call<NotasListResponse>, t: Throwable) {
                Toast.makeText(MyApp.instace, "Error. Can't connect to server", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        return notas
    }


    fun getNota(id: String): MutableLiveData<NotaDetailResponse> {
        val call: Call<NotaDetailResponse> = damKeepService.getNota(id)
        call.enqueue(object : Callback<NotaDetailResponse> {
            override fun onResponse(call: Call<NotaDetailResponse>, response: Response<NotaDetailResponse>) {
                if (!response.isSuccessful) {
                    Log.e("RequestError", response.message())
                    Toast.makeText(MyApp.instace, "", Toast.LENGTH_SHORT).show()
                } else {
                    nota.value = response.body()
                }
            }
            override fun onFailure(call: Call<NotaDetailResponse>, t: Throwable) {
                Toast.makeText(MyApp.instace, "Error. Can't connect to server", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        return nota
    }


    fun deleteNota(id : String){
        val call : Call<Void>? = damKeepService.deleteNota(id)
        call?.enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(!response.isSuccessful){
                    Toast.makeText(MyApp.instace, "Error, nota no eliminada correctamnente", Toast.LENGTH_SHORT).show()
                    Log.e("RequestError", response.message())

                }else{
                    Toast.makeText(MyApp.instace, "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(MyApp.instace, "Error. Can't connect to server", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun putNota(id : String, notaEditRequest: NotaEditRequest): MutableLiveData<NotaDetailResponse>{
        val call: Call<NotaDetailResponse>? = damKeepService.putNota(id,notaEditRequest)
        call?.enqueue(object : Callback<NotaDetailResponse>{
            override fun onResponse(call: Call<NotaDetailResponse>, response: Response<NotaDetailResponse>) {
                if(!response.isSuccessful){
                    Toast.makeText(MyApp.instace, "Error al editar la nota", Toast.LENGTH_SHORT).show()
                }else{
                    nota.value = response.body()
                }
            }
            override fun onFailure(call: Call<NotaDetailResponse>, t: Throwable) {
                Toast.makeText(MyApp.instace, "Error. Can't connect to server", Toast.LENGTH_SHORT).show()
            }
        })
        return nota
    }

    fun postNuevaNota(notaEditRequest: NotaEditRequest): MutableLiveData<NotaDetailResponse>{
        val call: Call<NotaDetailResponse>? = damKeepService.postNota(notaEditRequest)
        call?.enqueue(object : Callback<NotaDetailResponse>{
            override fun onResponse(call: Call<NotaDetailResponse>, response: Response<NotaDetailResponse>) {
                if(!response.isSuccessful){
                    Toast.makeText(MyApp.instace, "Error al crear la nota", Toast.LENGTH_SHORT).show()
                }else{
                    nota.value = response.body()
                }
            }
            override fun onFailure(call: Call<NotaDetailResponse>, t: Throwable) {
                Toast.makeText(MyApp.instace, "Error. Can't connect to server", Toast.LENGTH_SHORT).show()
            }
        })
        return nota
    }


}