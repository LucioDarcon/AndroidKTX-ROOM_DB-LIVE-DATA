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
import com.example.androidktx.converter.UserConverter.converterUserEntityToDomain
import com.example.androidktx.data.SecurityPreferences
import com.example.androidktx.data.model.User
import com.example.androidktx.databinding.MainFragmentBinding
import com.example.androidktx.ui.adapters.UserAdapter
import com.example.androidktx.ui.dialogs.UserDialog
import com.example.androidktx.ui.dialogs.UserDialogContract
import com.example.androidktx.viewmodels.UserViewModel
import com.example.core.repository.UserRepository
import com.example.core.provider.Providers
import kotlinx.coroutines.launch

class MainFragment(searchUser: String? = null) : Fragment(), UserDialogContract,
    UserAdapter.ClickUserItem {

    private var mUserAdapter                   = UserAdapter()
    private var mUserViewModel: UserViewModel? = null
    private var mSearchUser: String?           = searchUser ?: ""

    companion object {
        fun newInstance() = MainFragment()
        fun newInstance(searchUser: String?) = MainFragment(searchUser)

    }

    private var mView: MainFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.main_fragment,
            null,
            false
        )

        initAdapter()
        initViewModel()

        mView?.activityMainFloatActionButton?.setOnClickListener { view ->
            val mDialog = context?.let { context -> UserDialog(context, this) }
            mDialog?.show()
        }

        return mView?.root
    }

    private fun initViewModel() {
        mUserViewModel = ViewModelProvider(
            this, UserViewModel
                .UserViewModelFactory(UserRepository(Providers.provideUserDao(context)), mSearchUser)
        ).get(UserViewModel::class.java)
    }

    private fun initAdapter() {
        mView?.activityMainRecyclerView?.layoutManager = LinearLayoutManager(context)
        mView?.activityMainRecyclerView?.adapter       = mUserAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUsers()
    }

    private fun getUsers() {
        mUserViewModel?.usersLiveData?.observe(viewLifecycleOwner, Observer { users ->
            mUserAdapter.submitList(converterUserEntityToDomain(users), this@MainFragment)
        })
    }

    override fun getSupportFragmentManager(): FragmentManager? {
        return activity?.supportFragmentManager
    }

    override fun getOwner(): ViewModelStore {
        return viewModelStore
    }

    override fun getViewLifecycle(): LifecycleOwner {
        return viewLifecycleOwner
    }

    override fun onClickCardItem(user: User) {
        activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, UserDetailFragment.newInstance(user))
                ?.addToBackStack("UserDetailFragment")
                ?.commit()
    }

    override fun onLongClickCardItem(user: User) {
        val b = ""
    }


}