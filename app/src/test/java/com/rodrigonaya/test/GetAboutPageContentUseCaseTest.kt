package com.rodrigonaya.test

import com.google.common.truth.Truth
import com.rodrigonaya.test.data.local.dao.PageDao
import com.rodrigonaya.test.data.local.entity.Page
import com.rodrigonaya.test.data.remote.ApiService
import com.rodrigonaya.test.data.repository.ApiRepositoryImpl
import com.rodrigonaya.test.domain.repository.ApiRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetAboutPageContentUseCaseTest {

    private lateinit var mockApiRepository: ApiRepository
    @Before
    fun before() = runTest {
        val pageContentTest = "<p> Compass Hello World </p>"
        val mockApiService = object : ApiService {
            override suspend fun getAboutPage(): String {
                return pageContentTest
            }
        }
        val mockPageDao = mock(PageDao::class.java)
        `when`(mockPageDao.getPageData()).thenReturn(Page(pageContentTest))
        mockApiRepository = ApiRepositoryImpl(mockApiService, mockPageDao)
    }

    @Test
    fun `given a success call, a non-empty string is returned`() = runTest {

        val result = mockApiRepository.getAboutPageContent()
        Truth.assertThat(result).isInstanceOf(String::class.java)
        Truth.assertThat(result).isNotEmpty()
    }

}