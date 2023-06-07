package com.example.playme.firebase

import com.example.playme.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepClass @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRep {
    override fun login(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Load())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.toString()))
        }

    }

    override fun signUp(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Load())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.toString()))
        }
    }
}