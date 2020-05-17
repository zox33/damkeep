package com.vemiranda.damkeep.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vemiranda.damkeep.repository.DamKeepRepository
import com.vemiranda.damkeep.retrofit.request.LoginRequest
import com.vemiranda.damkeep.retrofit.responses.LoginResponse
import javax.inject.Inject

class LoginViewModel  @Inject constructor(damKeepRepository: DamKeepRepository): ViewModel(){
    val repository = damKeepRepository
    fun loguearse(loginRequest: LoginRequest): LiveData<LoginResponse> = repository.login(loginRequest)

}
