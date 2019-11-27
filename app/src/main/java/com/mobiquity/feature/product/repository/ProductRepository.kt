package com.mobiquity.feature.product.repository

import com.mobiquity.infrastructure.extensions.requestBlocking

class ProductRepository(
    private val accountServices: ProductServices
) : ProductOperations {
    override fun fetchProductList() = accountServices.fetchProducts().requestBlocking()


}