package com.example.androidktx.ui.adapters.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.androidktx.data.model.User
import com.example.androidktx.databinding.RecyclerItemUserBinding
import com.example.androidktx.ui.adapters.UserAdapter

class UserViewHolder(private val recyclerItemUserBinding: RecyclerItemUserBinding) : RecyclerView.ViewHolder(recyclerItemUserBinding.root) {

    fun bind(user: User, clickUserItem: UserAdapter.ClickUserItem?) {
        recyclerItemUserBinding.user = user

        recyclerItemUserBinding.recyclerItemUserCardView.setOnClickListener {
            clickUserItem?.onClickCardItem(user)
        }

        recyclerItemUserBinding.executePendingBindings()
    }


}