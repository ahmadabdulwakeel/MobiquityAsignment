package com.mobiquity.feature.product.repository

import com.mobiquity.data.tables.Category
import com.mobiquity.infrastructure.exception.Failure
import com.mobiquity.infrastructure.functional.Either
import com.mobiquity.infrastructure.platform.BaseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductUseCaseTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    @Mock
    private lateinit var none: BaseUseCase.None

    @Test
    fun run_SUCEESS() = runBlocking {
        val list: List<Category> = mock()
        val result: Either.Right<List<Category>> = mock()
        Mockito.`when`(result.b).thenReturn(list)

        Mockito.`when`(productRepository.fetchProductList()).thenReturn(result)
        val productUseCase = ProductUseCase(CoroutineScope(Dispatchers.Unconfined + Job()), productRepository, Dispatchers.Unconfined)
        Mockito.`when`(productUseCase.run(none)).thenReturn(result)

        productUseCase.invoke(none){
            assertEquals(it, result)
            assertNotNull(result.b)
        }

    }

    @Test
    fun run_Error() = runBlocking {
        val failure = Mockito.mock(Failure::class.java)
        val result: Either.Left<Failure> = mock()
        Mockito.`when`(result.a).thenReturn(failure)

        Mockito.`when`(productRepository.fetchProductList()).thenReturn(result)
        val productUseCase = ProductUseCase(CoroutineScope(Dispatchers.Unconfined + Job()), productRepository, Dispatchers.Unconfined)
        Mockito.`when`(productUseCase.run(none)).thenReturn(result)

        productUseCase.invoke(none){
            assertEquals(it, result)
            assertNotNull(result.a)
        }

    }
}
