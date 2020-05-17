package com.vemiranda.damkeep.common

import com.vemiranda.damkeep.LoginActivity
import com.vemiranda.damkeep.RegisterActivity
import com.vemiranda.damkeep.retrofit.NetworkModule
import com.vemiranda.damkeep.ui.DetailNotaActivity
import com.vemiranda.damkeep.ui.NuevaNotaActivity
import com.vemiranda.damkeep.ui.notas.notasFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(registerActivity: RegisterActivity)
    fun inject(fragment: notasFragment)
    fun inject(detailNotaActivity: DetailNotaActivity)
    fun inject(nuevaNotaActivity: NuevaNotaActivity)

}