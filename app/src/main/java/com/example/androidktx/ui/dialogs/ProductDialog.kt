package com.example.androidktx.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.androidktx.R
import com.example.androidktx.data.model.Product
import com.example.androidktx.databinding.ProductDialogBinding
import com.example.androidktx.ui.fragments.UserDetailFragment
import com.example.androidktx.viewmodels.ProductViewModel
import com.example.core.provider.Providers
import com.example.core.repository.ProductRepository

class ProductDialog(context: Context, private val view: ProductDialogContract, private val userId: Long? = null) : Dialog(context) {

    private lateinit var mDialog : ProductDialogBinding
    private var mProductViewModel: ProductViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDialog = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.product_dialog,
            null,
            false
        )
        setContentView(mDialog.root)
        configureWindow()
        configureButtonSaveDialog()
        initViewModel()
    }

    private fun configureButtonSaveDialog() {
        mDialog?.productDialogSaveButton.setOnClickListener {
            createProduct()
        }
    }

    private fun initViewModel() {
        mProductViewModel = ViewModelProvider(
            view.getOwner(), ProductViewModel.ProductViewModelFactory(ProductRepository(Providers.provideProductDao(context)), "")
        ).get(ProductViewModel::class.java)
    }

    private fun configureWindow() {
        this.window?.setLayout(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
    }

    private fun createProduct() {
        mProductViewModel?.createProduct(
            Product(
                userId = userId,
                name = mDialog.productDialogNameEditText.text.toString(),
                price = mDialog.productDialogPriceEditText.text.toString().toFloat(),
                description = mDialog.productDialogDescriptionEditText.text.toString(),
            )
        )
        this.dismiss()
    }


}