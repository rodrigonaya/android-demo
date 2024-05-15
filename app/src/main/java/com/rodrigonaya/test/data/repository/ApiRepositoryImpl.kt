package com.rodrigonaya.test.data.repository

import com.rodrigonaya.test.data.local.dao.PageDao
import com.rodrigonaya.test.data.local.entity.Page
import com.rodrigonaya.test.data.remote.ApiService
import com.rodrigonaya.test.domain.repository.ApiRepository

class ApiRepositoryImpl(
    private val apiService: ApiService,
    private val pageDao: PageDao
): ApiRepository {

    override suspend fun getAboutPageContent(): String? {
        try {
            val pageContent = apiService.getAboutPage()
            savePageContentToDatabase(pageContent)
            return pageContent
        } catch (e: Exception) {
            e.printStackTrace()
            val dbPageContent = pageDao.getPageData()
            return dbPageContent?.htmlContent
        }
    }

    private suspend fun savePageContentToDatabase(updatedRemoteContent: String) {
        val dbPage = pageDao.getPageData() ?: Page(htmlContent = "")
        val updatedDbPage = dbPage.copy(
            htmlContent = updatedRemoteContent
        )
        pageDao.upsertPage(updatedDbPage)
    }
}