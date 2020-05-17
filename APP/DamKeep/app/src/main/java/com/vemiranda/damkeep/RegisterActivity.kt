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
import com.vemiranda.damkeep.data.RegisterViewModel
import com.vemiranda.damkeep.retrofit.request.LoginRequest
import com.vemiranda.damkeep.retrofit.request.RegisterRequest
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {


    @BindView(R.id.editTextUsernameRegister)
    lateinit var etUsername: EditText
    @BindView(R.id.editTextEmailRegister)
    lateinit var etEmail: EditText
    @BindView(R.id.editTextFullNameRegister)
    lateinit var etFullname: EditText
    @BindView(R.id.editTextPasswordRegister)
    lateinit var etPassword: EditText
    @BindView(R.id.editTextRepeatPasswordRegister)
    lateinit var etPasswordRepeat: EditText
    @BindView(R.id.buttonRegisterRegister)
    lateinit var buttonRegistro: Button

    @Inject
    lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        (this.applicationContext as MyApp).appComponent.inject(this)

        ButterKnife.bind(this)

        buttonRegistro.setOnClickListener {
            val loginRequest = RegisterRequest(
                email = etEmail.text.toString(),
                fullName = etFullname.text.toString(),
                password = etPassword.text.toString(),
                password2 = etPasswordRepeat.text.toString(),
                username = etUsername.text.toString()
                )
            registerViewModel.registrarse(loginRequest).observe(this, Observer {
                if (it != null) {
                    login()
                }
            })
        }

    }

    fun login() {
        val login = Intent(this, LoginActivity::class.java)
        startActivity(login)
        finish()
    }
}