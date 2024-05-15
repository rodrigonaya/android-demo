package com.rodrigonaya.test

import com.rodrigonaya.test.domain.use_case.GetWordCountUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.rodrigonaya.test.domain.entity.WordCount

class GetWordCountUseCaseTest {

    @Test
    fun `given a success call, a list of word count is returned`() = runTest {
        val getWordCountUseCase = GetWordCountUseCase()
        val result = getWordCountUseCase("Test data")
        assertThat(result.data).isInstanceOf(List::class.java)
        assertThat(result.data?.first()).isInstanceOf(WordCount::class.java)
    }

    @Test
    fun `given a success call, each word is counted and returned`() = runTest {
        val getWordCountUseCase = GetWordCountUseCase()
        val wordCountResult = getWordCountUseCase("<p> Compass Hello World </p>").data
        assertThat(wordCountResult).containsExactlyElementsIn(
            listOf(
                WordCount("<p>", 1),
                WordCount("Compass", 1),
                WordCount("Hello", 1),
                WordCount("World", 1),
                WordCount("</p>", 1)
            )
        )
    }
}