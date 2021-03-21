package com.example.androidktx.ui.adapters.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidktx.R
import com.example.androidktx.data.model.User
import com.example.androidktx.databinding.RecyclerItemUserBinding

class UserViewHolder(private val recyclerItemUserBinding: RecyclerItemUserBinding) : RecyclerView.ViewHolder(recyclerItemUserBinding.root) {

    fun bind(user: User) {
        recyclerItemUserBinding.user = user
        recyclerItemUserBinding.executePendingBindings()
    }


}