package com.vemiranda.damkeep.ui.notas

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.vemiranda.damkeep.ui.DetailNotaActivity
import com.vemiranda.damkeep.R
import com.vemiranda.damkeep.common.Constants
import com.vemiranda.damkeep.retrofit.responses.NotasListResponseItem


import kotlinx.android.synthetic.main.fragment_notas.view.*


class MynotasRecyclerViewAdapter(
) : RecyclerView.Adapter<MynotasRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var notas: List<NotasListResponseItem> = ArrayList()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as NotasListResponseItem
            val idNota = item.id
            val intent2= Intent(v.context, DetailNotaActivity::class.java).apply {
                putExtra(Constants.ID_NOTA,idNota)
            }
           startActivity(v.context, intent2, Bundle())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_notas, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notas[position]
        holder.tvTitulo.text = item.titulo
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = notas.size
    fun setData(notasUser: List<NotasListResponseItem>?) {
        notas = notasUser!!
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val tvTitulo: TextView = mView.textViewTitutloNotaList
    }

}
