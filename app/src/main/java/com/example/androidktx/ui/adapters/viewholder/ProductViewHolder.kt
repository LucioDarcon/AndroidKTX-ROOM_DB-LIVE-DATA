package com.example.androidktx.ui.adapters.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.androidktx.data.model.Product
import com.example.androidktx.databinding.RecyclerItemUserBinding
import com.example.androidktx.databinding.RecyclerViewProductBinding

class ProductViewHolder(private val recyclerItemProductBinding: RecyclerViewProductBinding) : RecyclerView.ViewHolder(recyclerItemProductBinding.root) {

    fun bind(product: Product) {
        recyclerItemProductBinding.product = product
        recyclerItemProductBinding.executePendingBindings()
    }

}