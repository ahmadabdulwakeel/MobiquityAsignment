package com.mobiquity.di.productModule

import com.mobiquity.feature.product.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productViewModelModule = module {
    viewModel { ProductViewModel(get(), get()) }
}