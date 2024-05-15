package com.rodrigonaya.test.domain.use_case

import com.rodrigonaya.test.common.Resource
import com.rodrigonaya.test.domain.repository.ApiRepository

class GetAboutPageContentUseCase(
    private val apiRepository: ApiRepository
) {

    suspend operator fun invoke(): Resource<String?> {
        try {
            val pageContent = apiRepository.getAboutPageContent()
            return Resource.Success(pageContent)
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage ?: "Unexpected error")
        }
    }

}