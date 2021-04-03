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

    private var users: List<User>?              = ArrayList()
    private var mClickUserItem : ClickUserItem? = null

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
                users?.get(position)?.let { holder.bind(it, mClickUserItem) }
            }
        }
    }

    fun submitList(listUsers: List<User>?, clickUserItem: ClickUserItem) {
        this.users          = listUsers
        this.mClickUserItem = clickUserItem
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return users?.size!!
    }

    interface ClickUserItem {

        fun onClickCardItem(user: User)
        fun onLongClickCardItem(user: User)


    }


}