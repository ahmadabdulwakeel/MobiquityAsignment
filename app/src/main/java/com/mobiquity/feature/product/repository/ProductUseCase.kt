package com.mobiquity.feature.product.repository
import com.mobiquity.data.tables.Category
import com.mobiquity.infrastructure.platform.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

//TODO Added Dispatchers.Main for unit testing. Need to replace it with proper handling of main and background dispatcher
class ProductUseCase(ioScope: CoroutineScope, private val productRepository: ProductRepository, main : CoroutineDispatcher):
    BaseUseCase<List<Category>, BaseUseCase.None>(ioScope, main) {
    override suspend fun run(param: None)=productRepository.fetchProductList()

}