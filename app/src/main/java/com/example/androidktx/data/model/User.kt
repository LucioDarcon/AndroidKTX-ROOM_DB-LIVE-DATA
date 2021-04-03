package com.example.androidktx.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val id: Long?      = null,
                val name: String?  = null,
                val email: String? = null) : Parcelable