package com.mobiquity.feature.product.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mobiquity.data.tables.Category
import com.mobiquity.feature.product.repository.ProductUseCase
import com.mobiquity.infrastructure.platform.BaseUseCase
import com.mobiquity.infrastructure.platform.BaseViewModel

class ProductViewModel(
    androidApplication: Application,
    private val productUseCase: ProductUseCase

) : BaseViewModel(androidApplication) {

    //region Observable Data
    var products: MutableLiveData<List<Category>> = MutableLiveData()

    //endregion
    //region Remotes
    fun fetchProducts(params: BaseUseCase.None) = productUseCase(params)
    {
        it.either(::handleFailure) {
            products.postValue( it)
        }

    }

}