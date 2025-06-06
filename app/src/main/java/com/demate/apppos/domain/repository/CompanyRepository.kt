package com.demate.apppos.domain.repository

import com.demate.apppos.domain.model.Company
import com.demate.apppos.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CompanyRepository {
    suspend fun registerCompany(company: Company): Flow<Resource<Company>>
    suspend fun getCompanyByUserId(userId: String): Flow<Resource<Company?>>
    suspend fun updateCompany(company: Company): Flow<Resource<Company>>
}