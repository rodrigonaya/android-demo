package com.rodrigonaya.test

import android.app.Application
import com.rodrigonaya.test.data.local.MainDatabase
import com.rodrigonaya.test.data.remote.ApiService
import com.rodrigonaya.test.data.repository.ApiRepositoryImpl
import com.rodrigonaya.test.domain.repository.ApiRepository
import com.rodrigonaya.test.domain.use_case.GetAboutPageContentUseCase
import com.rodrigonaya.test.domain.use_case.GetEvery10thCharacterUseCase
import com.rodrigonaya.test.domain.use_case.GetWordCountUseCase
import com.rodrigonaya.test.presentation.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class TestApplication : Application() {

    private val networkModule = module {
        single<ApiService> {
            Retrofit.Builder()
                .baseUrl("https://www.compass.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

    private val databaseModule = module {
        single { MainDatabase.getDatabase(get()).pageDao() }
    }

    private val repositoryModule = module {
        single<ApiRepository> { ApiRepositoryImpl(get(), get()) }
    }

    private val useCaseModule = module {
        single { GetAboutPageContentUseCase(get()) }
        single { GetEvery10thCharacterUseCase() }
        single { GetWordCountUseCase() }
    }

    private val viewModelModule = module {
        viewModel { MainViewModel(get(), get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestApplication)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}