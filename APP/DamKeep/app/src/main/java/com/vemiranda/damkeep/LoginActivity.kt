package com.vemiranda.damkeep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import com.vemiranda.damkeep.common.Constants
import com.vemiranda.damkeep.common.MyApp
import com.vemiranda.damkeep.common.SharedPreferencesManager
import com.vemiranda.damkeep.data.LoginViewModel
import com.vemiranda.damkeep.retrofit.request.LoginRequest
import com.vemiranda.damkeep.ui.MainActivity
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @BindView(R.id.editTextUsername)
    lateinit var etUsername: EditText
    @BindView(R.id.editTextPassword)
    lateinit var etPassword: EditText
    @BindView(R.id.buttonLogin)
    lateinit var buttonLogin: Button
    @BindView(R.id.buttonRegister)
    lateinit var buttonRegister: Button
    @Inject
    lateinit var loginViewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)

        (this.applicationContext as MyApp).appComponent.inject(this)

        val token: String? = SharedPreferencesManager().getSharedPreferences()
            .getString(Constants.TOKEN, "")

        if (!token.isNullOrEmpty()) {
            iniciar()
        }

        buttonLogin.setOnClickListener {
            val loginRequest = LoginRequest(username = etUsername.text.toString(), password = etPassword.text.toString())
            loginViewModel.loguearse(loginRequest).observe(this, Observer {
                if(it != null) {
                    iniciar()
                }
            })
        }


        buttonRegister.setOnClickListener {
           val registro = Intent(this, RegisterActivity::class.java).apply{
               flags = Intent.FLAG_ACTIVITY_NEW_TASK
           }
            startActivity(registro)
        }
    }

    fun iniciar() {
        val inicio = Intent(this, MainActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(inicio)
        finish()
    }

}
