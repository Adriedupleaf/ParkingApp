package com.example.domain.models

import android.net.Uri

data class CDUser(
    val name: String? = null,
    val email: String? = null,
    val photoUrl: Uri? = null,
    val emailVerified: Boolean? = false,
    val uId: String? = null,
    val parameters: Map<String,Any>? = null
)