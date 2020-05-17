package com.vemiranda.damkeep.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vemiranda.damkeep.repository.DamKeepRepository
import com.vemiranda.damkeep.retrofit.request.LoginRequest
import com.vemiranda.damkeep.retrofit.request.RegisterRequest
import com.vemiranda.damkeep.retrofit.responses.LoginResponse
import com.vemiranda.damkeep.retrofit.responses.RegisterResponse
import javax.inject.Inject

class RegisterViewModel  @Inject constructor(damKeepRepository: DamKeepRepository): ViewModel(){
    val repository = damKeepRepository
    fun registrarse(registerRequest: RegisterRequest): LiveData<RegisterResponse> = repository.register(registerRequest)
}
