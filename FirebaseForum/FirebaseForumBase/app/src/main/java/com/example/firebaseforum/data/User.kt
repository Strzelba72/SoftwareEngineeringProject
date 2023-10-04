package com.example.firebaseforum.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val email:String? = null) : Parcelable