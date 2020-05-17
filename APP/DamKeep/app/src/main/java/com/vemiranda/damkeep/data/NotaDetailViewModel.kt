package com.vemiranda.damkeep.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vemiranda.damkeep.repository.DamKeepRepository
import com.vemiranda.damkeep.retrofit.request.NotaEditRequest
import com.vemiranda.damkeep.retrofit.responses.NotaDetailResponse
import com.vemiranda.damkeep.retrofit.responses.NotasListResponse
import javax.inject.Inject

class NotaDetailViewModel @Inject constructor(
    damKeepRepository: DamKeepRepository
): ViewModel(){

    private var repo = damKeepRepository
    fun getNota(id:String): LiveData<NotaDetailResponse> {
        return repo.getNota(id)
    }

    fun deleteNota(id:String){
        return repo.deleteNota(id)
    }

}

