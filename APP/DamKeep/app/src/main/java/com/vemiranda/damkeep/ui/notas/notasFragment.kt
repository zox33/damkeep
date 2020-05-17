package com.vemiranda.damkeep.ui.notas

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.vemiranda.damkeep.R
import com.vemiranda.damkeep.common.MyApp
import com.vemiranda.damkeep.data.NotaViewModel
import com.vemiranda.damkeep.retrofit.responses.NotasListResponseItem
import javax.inject.Inject


class notasFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    @Inject lateinit var notaViewModel: NotaViewModel
    private lateinit var notaAdapter: MynotasRecyclerViewAdapter
    private var notas: List<NotasListResponseItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notas_list, container, false)
        notaAdapter = MynotasRecyclerViewAdapter()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = notaAdapter
            }
        }

        notaViewModel.getNotas().observe(viewLifecycleOwner, Observer {
            notas = it
            notaAdapter.setData(notas)
        })

        return view
    }



}
