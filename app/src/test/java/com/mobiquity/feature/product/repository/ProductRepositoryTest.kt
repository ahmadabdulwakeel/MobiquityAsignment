package com.mobiquity.feature.product.repository

import com.mobiquity.data.tables.Category
import com.mobiquity.di.productModule.productRepositoryModule
import com.mobiquity.infrastructure.functional.Either
import com.mobiquity.infrastructure.networks.networkModule
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ProductRepositoryTest: AutoCloseKoinTest() {

    val productServices by inject<ProductServices>()

    @Test
    fun fetchProductList_SUCCESS() {

        val serviceRepository = ProductRepository(productServices)
        val serviceCall: Call<List<Category>> = mock()
        val retrofitResponse: Response<List<Category>> = mock()
        val responseBody: List<Category> = mock()

        Mockito.`when`(serviceCall.execute()).thenReturn(retrofitResponse)
        Mockito.`when`(retrofitResponse.isSuccessful).thenReturn(true)
        Mockito.`when`(retrofitResponse.body()).thenReturn(responseBody)

        Mockito.`when`(productServices.fetchProducts()).thenReturn(serviceCall)
        val response = serviceRepository.fetchProductList()
        assertTrue(response is Either.Right<*>)
    }

    @Test
    fun fetchProductList_ERROR() {

        val serviceRepository = ProductRepository(productServices)
        val serviceCall: Call<List<Category>> = mock()
        val retrofitResponse: Response<List<Category>> = mock()
        val responseBody: List<Category> = mock()

        Mockito.`when`(serviceCall.execute()).thenReturn(retrofitResponse)
        Mockito.`when`(retrofitResponse.isSuccessful).thenReturn(false)

        Mockito.`when`(productServices.fetchProducts()).thenReturn(serviceCall)
        val response = serviceRepository.fetchProductList()
        assertTrue(response is Either.Left<*>)
    }

    @Before
    fun before() {
        startKoin {
            modules(listOf(
                networkModule,
                productRepositoryModule
            ))
        }
        declareMock<ProductServices>()
    }

}
inline fun <reified T : Any> mock() = Mockito.mock(T::class.java)