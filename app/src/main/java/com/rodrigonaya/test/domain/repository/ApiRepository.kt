package com.rodrigonaya.test.domain.repository

interface ApiRepository {
    suspend fun getAboutPageContent(): String?
}