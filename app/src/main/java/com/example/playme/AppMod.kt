package com.example.playme

import com.example.playme.firebase.AuthRep
import com.example.playme.firebase.AuthRepClass
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppMod {

    @Provides
    @Singleton
    fun providesAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepImpl(firebaseAuth: FirebaseAuth): AuthRep {
        return AuthRepClass(firebaseAuth)
    }
}
