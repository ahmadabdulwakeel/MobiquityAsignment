package com.mobiquity.feature.product.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mobiquity.data.tables.Category
import com.mobiquity.feature.product.repository.ProductRepository
import com.mobiquity.feature.product.repository.ProductUseCase
import com.mobiquity.infrastructure.exception.Failure
import com.mobiquity.infrastructure.functional.Either
import com.mobiquity.infrastructure.platform.BaseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductViewModelTest {

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var successObserver: Observer<List<Category>>

    @Mock
    private lateinit var failureObserver: Observer<Failure>

    @Mock
    private lateinit var none: BaseUseCase.None

    @JvmField
    @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun fetchProducts_SUCCESS()= runBlocking {

        val list = emptyList<Category>()
        val result = Either.Right(list)

        val productRepository = Mockito.mock(ProductRepository::class.java)
        Mockito.`when`(productRepository.fetchProductList()).thenReturn(result)
        val productUseCase =
            ProductUseCase(CoroutineScope(Dispatchers.Unconfined + Job()), productRepository, Dispatchers.Unconfined)
        val servicesViewModel = ProductViewModel(application, productUseCase)

        Mockito.`when`(productUseCase.run(none)).thenReturn(result)
        servicesViewModel.products.observeForever(successObserver)

        servicesViewModel.fetchProducts(none)
        Mockito.verify(successObserver).onChanged(list)
    }

    @Test
    fun fetchProducts_Failure() = runBlocking {

        val failure = Failure.ServerError
        val result = Either.Left(failure)

        val productRepository = Mockito.mock(ProductRepository::class.java)
        Mockito.`when`(productRepository.fetchProductList()).thenReturn(result)
        val productUseCase =
            ProductUseCase(CoroutineScope(Dispatchers.Unconfined + Job()), productRepository, Dispatchers.Unconfined)
        val servicesViewModel = ProductViewModel(application, productUseCase)

        Mockito.`when`(productUseCase.run(none)).thenReturn(result)
        servicesViewModel.failure.observeForever(failureObserver)

        servicesViewModel.fetchProducts(none)

        Mockito.verify(failureObserver).onChanged(failure)

    }
}