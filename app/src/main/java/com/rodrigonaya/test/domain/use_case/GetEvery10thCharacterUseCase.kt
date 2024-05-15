package com.rodrigonaya.test.domain.use_case

import com.rodrigonaya.test.common.Resource
import com.rodrigonaya.test.domain.repository.ApiRepository

class GetEvery10thCharacterUseCase {

    operator fun invoke(pageData: String): Resource<String?> {
        try {
            val every10thCharacter = getEvery10thCharacter(pageData)
            return Resource.Success(
                every10thCharacter
            )
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage ?: "Unexpected error")
        }
    }

    private fun getEvery10thCharacter(pageData: String): String {
        val result = mutableListOf<Char>()
        for (i in pageData.indices) {
            if ((i + 1) % 10 == 0) {
                result.add(pageData[i])
            }
        }
        return result.toString()
    }

}