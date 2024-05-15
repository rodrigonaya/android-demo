package com.rodrigonaya.test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigonaya.test.common.Resource
import com.rodrigonaya.test.domain.entity.WordCount
import com.rodrigonaya.test.domain.use_case.GetAboutPageContentUseCase
import com.rodrigonaya.test.domain.use_case.GetEvery10thCharacterUseCase
import com.rodrigonaya.test.domain.use_case.GetWordCountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val getAboutPageContentUseCase: GetAboutPageContentUseCase,
    val getEvery10thCharacterUseCase: GetEvery10thCharacterUseCase,
    val getWordCountUseCase: GetWordCountUseCase
) : ViewModel() {

    private val _every10thCharacterList = MutableStateFlow("")
    val every10thCharacter = _every10thCharacterList.asStateFlow()

    private val _wordCountList = MutableStateFlow<List<WordCount>>(emptyList())
    val wordCount = _wordCountList.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun getEvery10thCharacterList() {
        viewModelScope.launch {
            getAboutPageContent()?.let { pageContent ->
                when (val result = getEvery10thCharacterUseCase(pageContent)) {
                    is Resource.Error -> {
                        _error.emit(result.message)
                    }

                    is Resource.Success -> {
                        _error.value = null
                        _every10thCharacterList.value = result.data ?: ""
                    }
                }
            }
        }
    }

    fun getWordCount() {
        viewModelScope.launch {
            getAboutPageContent()?.let { pageContent ->
                when (val result = getWordCountUseCase(pageContent)) {
                    is Resource.Error -> {
                        _error.emit(result.message)
                    }

                    is Resource.Success -> {
                        _wordCountList.value = result.data ?: listOf()
                    }
                }
            }

        }
    }

    private suspend fun getAboutPageContent(): String? {
        when(val result = getAboutPageContentUseCase()) {
            is Resource.Error -> {
                _error.emit(result.message)
                return null
            }

            is Resource.Success -> {
                return result.data
            }
        }
    }
}