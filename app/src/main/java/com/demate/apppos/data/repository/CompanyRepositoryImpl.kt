package com.demate.apppos.data.repository

import android.content.Context
import com.demate.apppos.R
import com.demate.apppos.domain.model.Company
import com.demate.apppos.domain.repository.CompanyRepository
import com.demate.apppos.domain.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val context: Context
) : CompanyRepository {

    private val companyCollection = firestore.collection("companies")

    override suspend fun registerCompany(company: Company): Flow<Resource<Company>> = callbackFlow {
        trySend(Resource.Loading())

        try {
            val companyId = company.id.ifEmpty { companyCollection.document().id }
            val newCompany = company.copy(id = companyId)

            companyCollection.document(companyId)
                .set(newCompany)
                .await()

            trySend(Resource.Success(newCompany))
        } catch (e: Exception) {
            val errorMessage = context.getString(R.string.error_registering_company)
            trySend(Resource.Error(e.message ?: errorMessage))
        }

        awaitClose()
    }

    override suspend fun getCompanyByUserId(userId: String): Flow<Resource<Company?>> =
        callbackFlow {
            trySend(Resource.Loading())

            try {
                val snapshot = companyCollection
                    .whereEqualTo("userId", userId)
                    .limit(1)
                    .get()
                    .await()

                if (snapshot.isEmpty) {
                    trySend(Resource.Success(null))
                } else {
                    val company = snapshot.documents[0].toObject(Company::class.java)
                    trySend(Resource.Success(company))
                }
            } catch (e: Exception) {
                val errorMessage = context.getString(R.string.company_not_found)
                trySend(Resource.Error(e.message ?: errorMessage))
            }

            awaitClose()
        }

    override suspend fun updateCompany(company: Company): Flow<Resource<Company>> = callbackFlow {
        trySend(Resource.Loading())

        try {
            companyCollection.document(company.id)
                .set(company)
                .await()

            trySend(Resource.Success(company))
        } catch (e: Exception) {
            val errorMessage = context.getString(R.string.error_updating_company)
            trySend(Resource.Error(e.message ?: errorMessage))
        }

        awaitClose()
    }
}