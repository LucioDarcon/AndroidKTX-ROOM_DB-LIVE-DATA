package com.example.androidktx.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.example.androidktx.R
import com.example.androidktx.databinding.UserDialogBinding

class UserDialog(context: Context) : Dialog(context) {

    private lateinit var mDialog: UserDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDialog = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.user_dialog,
            null,
            false
        )
        setContentView(mDialog.root)
        this.window?.setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
    }


}