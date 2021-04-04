package com.example.androidktx.ui.dialogs

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStore

interface ProductDialogContract {

    fun getViewLifecycle() : LifecycleOwner

    fun getOwner(): ViewModelStore

    fun getSupportFragmentManager(): FragmentManager?


}