package com.example.retrofit_ny.di

import com.example.retrofit_ny.data.ProductsApi
import com.example.retrofit_ny.data.repository.ProductRepository
import com.example.retrofit_ny.data.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://dummyjson.com/")
        .build()

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductsApi {
        return retrofit.create(ProductsApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface NetworkModuleInterface {

        @Binds
        @Singleton
        fun provideProductRepository(repositoryImpl: ProductRepositoryImpl): ProductRepository

    }
}