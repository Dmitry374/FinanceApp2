package com.dima.financeapp.di

import android.content.Context
import com.dima.financeapp.BuildConfig
import com.dima.financeapp.R
import com.dima.financeapp.common.Constants
import com.dima.financeapp.common.DataMapper
import com.dima.financeapp.domain.AuthorisationInteractor
import com.dima.financeapp.network.ApiService
import com.dima.financeapp.repository.AuthorisationRepository
import com.dima.financeapp.sharedpreference.SharedPreferenceHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        // init logging interceptor
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideDataMapper(): DataMapper {
        return DataMapper()
    }

    @Singleton
    @Provides
    fun provideAuthorisationRepository(apiService: ApiService, dataMapper: DataMapper): AuthorisationRepository {
        return AuthorisationRepository(apiService, dataMapper)
    }

    @Singleton
    @Provides
    fun provideSharedPreferenceHelper(context: Context): SharedPreferenceHelper {
        return SharedPreferenceHelper(
            context.getSharedPreferences(
                context.resources.getString(R.string.app_name),
                Context.MODE_PRIVATE
            )
        )
    }

    @Singleton
    @Provides
    fun provideAuthorisationInteractor(
        authorisationRepository: AuthorisationRepository,
        sharedPreferenceHelper: SharedPreferenceHelper
    ): AuthorisationInteractor {
        return AuthorisationInteractor(authorisationRepository, sharedPreferenceHelper)
    }
}