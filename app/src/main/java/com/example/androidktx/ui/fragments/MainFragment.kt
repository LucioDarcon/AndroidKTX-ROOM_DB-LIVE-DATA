package com.example.androidktx.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidktx.R
import com.example.androidktx.data.repository.UserRepositoryMock
import com.example.androidktx.databinding.MainFragmentBinding
import com.example.androidktx.ui.adapters.UserAdapter
import com.example.androidktx.viewmodels.UserViewModel
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var mUserAdapter                   = UserAdapter()
    private var mUserViewModel: UserViewModel? = null

    companion object {
        fun newInstance() = MainFragment()


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

        return mView?.root
    }

    private fun initAdapter() {
        mView?.activityMainRecyclerView?.layoutManager = LinearLayoutManager(context)
        mView?.activityMainRecyclerView?.adapter       = mUserAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getData()
    }

    private fun getData() {
        mUserViewModel = ViewModelProvider(
            this, UserViewModel
                .UserViewModelFactory(UserRepositoryMock())
        ).get(UserViewModel::class.java)

        lifecycleScope.launch {
            mUserViewModel?.getMockUsers()

            mUserViewModel?.usersLiveData?.observe(viewLifecycleOwner, Observer { users ->
                mUserAdapter.submitList(users)
            })
        }
    }

}