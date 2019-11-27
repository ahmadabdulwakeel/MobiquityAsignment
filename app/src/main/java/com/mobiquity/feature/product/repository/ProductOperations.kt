package com.mobiquity.feature.product.repository

import com.mobiquity.data.tables.Category
import com.mobiquity.infrastructure.exception.Failure
import com.mobiquity.infrastructure.functional.Either

interface ProductOperations {
    fun fetchProductList(): Either<Failure, List<Category>>
}