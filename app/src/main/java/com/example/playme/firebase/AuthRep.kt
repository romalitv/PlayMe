package com.example.playme.firebase

import com.example.playme.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRep {
    fun login(email : String, password : String) : Flow<Resource<AuthResult>>
    fun signUp(email: String, password: String) : Flow<Resource<AuthResult>>
}