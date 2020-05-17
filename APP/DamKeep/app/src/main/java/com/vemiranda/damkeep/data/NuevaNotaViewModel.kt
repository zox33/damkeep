package com.vemiranda.damkeep.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vemiranda.damkeep.repository.DamKeepRepository
import com.vemiranda.damkeep.retrofit.request.NotaEditRequest
import com.vemiranda.damkeep.retrofit.responses.NotaDetailResponse
import javax.inject.Inject

class NuevaNotaViewModel @Inject constructor(
    damKeepRepository: DamKeepRepository
): ViewModel(){

    private var repo = damKeepRepository
    fun nuevaNota(notaEditRequest: NotaEditRequest): LiveData<NotaDetailResponse> {
        return repo.postNuevaNota(notaEditRequest)
    }

    fun editNota(id:String, notaEditRequest: NotaEditRequest): LiveData<NotaDetailResponse> {
        return repo.putNota(id,notaEditRequest)
    }
}
