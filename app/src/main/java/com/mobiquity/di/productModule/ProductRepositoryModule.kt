package com.mobiquity.di.productModule

import com.mobiquity.feature.product.repository.ProductRepository
import com.mobiquity.feature.product.repository.ProductServices
import org.koin.dsl.module
import retrofit2.Retrofit

val productRepositoryModule = module {

    single{get<Retrofit>().create(ProductServices::class.java)}
    single{ ProductRepository(get()) }
}