package com.rodrigonaya.test.domain.use_case

import com.rodrigonaya.test.common.Resource
import com.rodrigonaya.test.domain.entity.WordCount
import com.rodrigonaya.test.domain.repository.ApiRepository

class GetWordCountUseCase {

    operator fun invoke(pageData: String): Resource<List<WordCount>> {
        try {
            val wordCount = getWordCount(pageData)
            return Resource.Success(
                wordCount
            )
        } catch (e: Exception) {
            return Resource.Error(e.localizedMessage ?: "Unexpected error")
        }
    }

    private fun getWordCount(pageContent: String?): List<WordCount> {
        if(pageContent == null) {
            return emptyList()
        }

        val words = pageContent.split("\\s+".toRegex()).filter { it.isNotEmpty() }
        val wordCount = mutableMapOf<String, Int>()
        for (word in words) {
            wordCount[word] = wordCount.getOrDefault(word, 0) + 1
        }

        val wordCountMutableList = mutableListOf<WordCount>()
        for ((word, count) in wordCount) {
            wordCountMutableList.add(
                WordCount(word, count)
            )
        }

        return wordCountMutableList
    }

}