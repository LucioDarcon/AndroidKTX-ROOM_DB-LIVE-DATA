package com.example.androidktx.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidktx.R
import com.example.androidktx.data.model.User
import com.example.androidktx.databinding.RecyclerItemUserBinding
import com.example.androidktx.ui.adapters.viewholder.UserViewHolder

class UserAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var users: List<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mCardUseAdapter = DataBindingUtil.inflate<RecyclerItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recycler_item_user,
            parent,
            false
        )

        return UserViewHolder(mCardUseAdapter)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> {
                holder.bind(users[position])
            }
        }
    }

    fun submitList(listUsers: List<User>) {
        this.users = listUsers
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return users.size
    }


}