package com.rodrigonaya.test.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("/about")
    suspend fun getAboutPage(): String
}