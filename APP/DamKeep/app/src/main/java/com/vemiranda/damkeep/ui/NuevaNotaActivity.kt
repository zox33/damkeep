package com.vemiranda.damkeep.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import com.vemiranda.damkeep.R
import com.vemiranda.damkeep.common.Constants
import com.vemiranda.damkeep.common.MyApp
import com.vemiranda.damkeep.data.NotaDetailViewModel
import com.vemiranda.damkeep.data.NuevaNotaViewModel
import com.vemiranda.damkeep.retrofit.request.NotaEditRequest
import javax.inject.Inject

class NuevaNotaActivity : AppCompatActivity() {


    @BindView(R.id.editTextTituloNuevaNota)
    lateinit var etTitulo: EditText
    @BindView(R.id.editTextDescripcionNotaNueva)
    lateinit var etDescripcion: EditText
    @BindView(R.id.buttonGuardarNota)
    lateinit var btnGuardar: Button
    @Inject
    lateinit var nuevaNotaViewModel: NuevaNotaViewModel
    @Inject
    lateinit var notaDetailViewModel: NotaDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_nota)
        ButterKnife.bind(this)



        (this.applicationContext as MyApp).appComponent.inject(this)

        val idNota = intent.getStringExtra(Constants.ID_NOTA)

        if(idNota.isNullOrEmpty()){
            btnGuardar.setOnClickListener {
                val nuevaNota =
                    NotaEditRequest(etDescripcion.text.toString(),etTitulo.text.toString())
                nuevaNotaViewModel.nuevaNota(nuevaNota)
                val inicio = Intent(this, MainActivity::class.java).apply{
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(inicio)
                finish()
            }
        }else {
            notaDetailViewModel.getNota(idNota).observe(this, Observer {
                etTitulo.setText(it.titulo)
                etDescripcion.setText(it.contenido)
            })
            btnGuardar.setOnClickListener {
                val notaActualizada =NotaEditRequest(etDescripcion.text.toString(),etTitulo.text.toString())
                nuevaNotaViewModel.editNota(idNota,notaActualizada).observe(this, Observer {
                    val inicio = Intent(this, MainActivity::class.java).apply{
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(inicio)
                    finish()
                })
            }
        }
    }
}
