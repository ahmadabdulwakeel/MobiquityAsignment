package com.mobiquity.di.productModule

import com.mobiquity.feature.product.repository.ProductUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val productUseCaseModule = module {
    single{ ProductUseCase(get(), get(), Dispatchers.Main) }
}