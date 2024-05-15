package com.rodrigonaya.test

import kotlinx.coroutines.test.runTest
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.rodrigonaya.test.domain.use_case.GetEvery10thCharacterUseCase

class GetEvery10thCharacterUseCaseTest {

    @Test
    fun `given a success call, a non-empty string is returned`() = runTest {
        val getEvery10thCharacterUseCase = GetEvery10thCharacterUseCase()
        val result = getEvery10thCharacterUseCase("Test data")
        assertThat(result.data).isInstanceOf(String::class.java)
        assertThat(result.data).isNotEmpty()
    }

    @Test
    fun `given a success call, each 10th character is found and returned`() = runTest {
        val getEvery10thCharacterUseCase = GetEvery10thCharacterUseCase()
        val every10thCharacter = getEvery10thCharacterUseCase("123456789009876543211234567890").data
        assertThat(every10thCharacter).isEqualTo("[0, 1, 0]")
    }
}