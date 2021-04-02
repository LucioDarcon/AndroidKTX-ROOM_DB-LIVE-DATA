package com.example.androidktx.ui.dialogs

import androidx.annotation.AnyThread
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import java.security.acl.Owner

interface UserDialogContract {

    fun getViewLifecycle() : LifecycleOwner

    fun getOwner(): ViewModelStore

    fun getLifecycleScoop() : LifecycleCoroutineScope

    fun getSupportFragmentManager(): FragmentManager?

}