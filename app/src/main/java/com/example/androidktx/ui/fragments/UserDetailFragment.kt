package com.example.androidktx.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidktx.R
import com.example.androidktx.converter.ProductConverter.converterProductEntityToProduct
import com.example.androidktx.data.model.Product
import com.example.androidktx.data.model.User
import com.example.androidktx.databinding.UserDetailFragmentBinding
import com.example.androidktx.ui.adapters.ProductAdapter
import com.example.androidktx.ui.dialogs.ProductDialog
import com.example.androidktx.ui.dialogs.ProductDialogContract
import com.example.androidktx.viewmodels.ProductViewModel
import com.example.androidktx.viewmodels.UserViewModel
import com.example.core.provider.Providers
import com.example.core.repository.ProductContract
import com.example.core.repository.ProductRepository
import com.example.core.repository.UserRepository
import kotlinx.coroutines.launch

class UserDetailFragment(private val currentUser: User? = null,
                         private val mSearchUser: String? = null) : Fragment(), ProductDialogContract {

    private var mView: UserDetailFragmentBinding? = null
    private var mProductAdapter : ProductAdapter? = null
    private var mProductViewModel: ProductViewModel? = null

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
        mView?.user = currentUser
        configureFloatButton()
        mView?.executePendingBindings()
        return mView?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()
        initViewModel()
        getProducts()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViewModel() {
        mProductViewModel = ViewModelProvider(
            this, ProductViewModel
                .ProductViewModelFactory(ProductRepository(Providers.provideProductDao(context)), mSearchUser, currentUser?.id)
        ).get(ProductViewModel::class.java)
    }

    private fun initAdapter() {
        mProductAdapter                                      = ProductAdapter()
        mView?.userDetailFragmentRecyclerView?.adapter       = mProductAdapter
        mView?.userDetailFragmentRecyclerView?.layoutManager = LinearLayoutManager(context)
    }

    private fun getProducts() {
        mProductViewModel?.productLiveData?.observe(viewLifecycleOwner, Observer {
            mProductAdapter?.submitList(converterProductEntityToProduct(it))
        })

    }

    private fun configureFloatButton() {
        mView?.userDetailFragmentFloatActionButton?.setOnClickListener { view ->
            context?.let { it -> ProductDialog(it, this, currentUser?.id).show() }
        }
    }

    override fun getViewLifecycle(): LifecycleOwner {
        return viewLifecycleOwner
    }

    override fun getOwner(): ViewModelStore {
        return viewModelStore
    }

    override fun getSupportFragmentManager(): FragmentManager? {
        return activity?.supportFragmentManager
    }


}