package com.example.androidktx.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidktx.R
import com.example.androidktx.data.model.Product
import com.example.androidktx.data.model.User
import com.example.androidktx.databinding.UserDetailFragmentBinding
import com.example.androidktx.ui.adapters.ProductAdapter

class UserDetailFragment(user: User) : Fragment() {

    private val mCurrentUser                      = user
    private var mView: UserDetailFragmentBinding? = null
    private var mProductAdapter : ProductAdapter? = null

    companion object {
        fun newInstance(user: User) : UserDetailFragment {
            return UserDetailFragment(user)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.user_detail_fragment,
            null,
            false
        )
        mView?.user = mCurrentUser
        mView?.executePendingBindings()
        return mView?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()
        mProductAdapter?.submitList(createMockListProduct())
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initAdapter() {
        mProductAdapter = ProductAdapter()
        mView?.userDetailFragmentRecyclerView?.adapter = mProductAdapter
        mView?.userDetailFragmentRecyclerView?.layoutManager = LinearLayoutManager(context)
    }

    private fun createMockListProduct(): List<Product> {
        return listOf(
            Product(name = "Keyboard", description = "this keyboard....", price = 10F),
            Product(name = "Keyboard 2", description = "this keyboard 2....", price = 20F),
            Product(name = "Keyboard 3", description = "this keyboard3....", price = 30F)
        )
    }



}