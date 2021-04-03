package com.example.androidktx.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidktx.R
import com.example.androidktx.data.model.User
import com.example.androidktx.databinding.UserDialogBinding
import com.example.androidktx.ui.fragments.MainFragment
import com.example.androidktx.viewmodels.UserViewModel
import com.example.core.datasource.UserLocalRepository
import com.example.core.provider.Providers
import kotlinx.coroutines.launch

class UserDialog(context: Context, view: UserDialogContract) : Dialog(context) {

    private lateinit var mDialog: UserDialogBinding
    private var mUserViewModel: UserViewModel? = null
    private var mView = view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDialog = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.user_dialog,
            null,
            false
        )
        setContentView(mDialog.root)
        this.window?.setLayout(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )

        mDialog.userDialogSaveUserButton.setOnClickListener {
            createUser(User(name = mDialog.userDialogNameEditText.text.toString(),
                            email = mDialog.userDialogEmailEditText.text.toString()))
            this.dismiss()
        }
    }

    private fun createUser(user: User) {
        mUserViewModel = ViewModelProvider(
            mView.getOwner(), UserViewModel.UserViewModelFactory(UserLocalRepository(Providers.provideUserDao(context)), "")
        ).get(UserViewModel::class.java)

        mView.getLifecycleScoop().launch {
            mUserViewModel?.createUser(user)

            mUserViewModel?.usersLiveData?.observe(mView.getViewLifecycle(), Observer { user ->
                mView.getSupportFragmentManager()?.beginTransaction()
                    ?.replace(R.id.container, MainFragment.newInstance())
                    ?.commitNow()
            })
        }
    }


}