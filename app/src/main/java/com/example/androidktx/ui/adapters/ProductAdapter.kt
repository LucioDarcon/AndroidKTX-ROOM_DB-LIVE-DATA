package com.example.androidktx.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidktx.R
import com.example.androidktx.data.model.Product
import com.example.androidktx.databinding.RecyclerItemUserBinding
import com.example.androidktx.databinding.RecyclerViewProductBinding
import com.example.androidktx.ui.adapters.viewholder.ProductViewHolder
import com.example.androidktx.ui.adapters.viewholder.UserViewHolder

class ProductAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mListProduct : List<Product>? = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mCardProductAdapter = DataBindingUtil.inflate<RecyclerViewProductBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_product,
            parent,
            false
        )

        return ProductViewHolder(mCardProductAdapter)
    }

    fun submitList(productList: List<Product>?) {
        mListProduct = productList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mListProduct?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductViewHolder -> {
                mListProduct?.get(position)?.let { holder.bind(it) }
            }
        }
    }
}