package com.vemiranda.damkeep.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.santiagorodriguezalberto.damkeepapp.model.User
import com.vemiranda.damkeep.repository.DamKeepRepository
import com.vemiranda.damkeep.retrofit.request.LoginRequest
import com.vemiranda.damkeep.retrofit.request.NotaEditRequest
import com.vemiranda.damkeep.retrofit.responses.LoginResponse
import com.vemiranda.damkeep.retrofit.responses.NotaDetailResponse
import com.vemiranda.damkeep.retrofit.responses.NotasListResponse
import javax.inject.Inject

class NotaViewModel  @Inject constructor(
damKeepRepository: DamKeepRepository
): ViewModel(){

    private var notas = damKeepRepository.getNotasUsuario()
    fun getNotas(): LiveData<NotasListResponse>{
        return notas
    }
  
}

