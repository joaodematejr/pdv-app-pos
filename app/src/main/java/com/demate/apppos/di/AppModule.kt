package com.demate.apppos.di

import android.app.Application
import android.content.Context
import com.demate.apppos.data.repository.AuthRepositoryImpl
import com.demate.apppos.data.repository.CompanyRepositoryImpl
import com.demate.apppos.data.session.SessionManager
import com.demate.apppos.domain.repository.AuthRepository
import com.demate.apppos.domain.repository.CompanyRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore,
        application: Application
    ): AuthRepository {
        return AuthRepositoryImpl(auth, firestore, application)
    }

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideCompanyRepository(
        firestore: FirebaseFirestore,
        application: Application
    ): CompanyRepository {
        return CompanyRepositoryImpl(firestore, application)
    }

    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }

}