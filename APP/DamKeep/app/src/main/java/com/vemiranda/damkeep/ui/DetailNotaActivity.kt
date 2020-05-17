package com.vemiranda.damkeep.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vemiranda.damkeep.R
import com.vemiranda.damkeep.common.Constants
import com.vemiranda.damkeep.common.MyApp
import com.vemiranda.damkeep.data.LoginViewModel
import com.vemiranda.damkeep.data.NotaDetailViewModel
import javax.inject.Inject

class DetailNotaActivity : AppCompatActivity() {

    @BindView(R.id.textViewTituloNotaDetail)
    lateinit var tvTitulo: TextView
    @BindView(R.id.textViewContenidoNota)
    lateinit var tvContenido: TextView
    @BindView(R.id.textViewFechaCreacion)
    lateinit var tvFechaCreacion: TextView
    @BindView(R.id.textViewFechaEdicion)
    lateinit var tvFechaEdicion: TextView

    @BindView(R.id.floatingActionButtonDelete)
    lateinit var floatButtonDelete: FloatingActionButton
    @BindView(R.id.floatingActionButtonEdit)
    lateinit var floatButtonEdit: FloatingActionButton

    @Inject
    lateinit var detailViewModel: NotaDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_nota)
        ButterKnife.bind(this)

        (this.applicationContext as MyApp).appComponent.inject(this)

        val idNota = intent.getStringExtra(Constants.ID_NOTA)

        floatButtonDelete.setOnClickListener{
            detailViewModel.deleteNota(idNota)
            val inicio = Intent(this, MainActivity::class.java).apply{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(inicio)
            finish()
        }

        floatButtonEdit.setOnClickListener{
            val edit = Intent(this, NuevaNotaActivity::class.java).apply{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(Constants.ID_NOTA,idNota)
            }
            startActivity(edit)
            finish()
        }

        detailViewModel.getNota(idNota).observe(this, Observer {
            tvTitulo.text = it.titulo
            tvContenido.text = it.contenido
            tvFechaCreacion.text = "Creado el ${it.fechaCreacion}"
            tvFechaEdicion.text = "Editado el ${it.ultimaEdicion}"
        })

    }
}
