package com.mobiquity.feature.product.ui

import android.app.Application
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mobiquity.R
import com.mobiquity.data.tables.Category
import com.mobiquity.data.tables.Product
import com.mobiquity.feature.product.repository.ProductUseCase
import com.mobiquity.feature.product.viewmodel.ProductViewModel
import com.mobiquity.matcher.RecyclerViewMatcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
open class ProductListFragmentTest: KoinTest{
    private lateinit var productViewModel: ProductViewModel
    @Mock
    private lateinit var productUseCase: ProductUseCase

    @Test
    fun launchFragmentAndVerifyDataPopulation() {

        val scenario = launchFragmentInContainer<ProductListFragment>(themeResId = R.style.AppTheme)
        scenario.onFragment {

            productViewModel.products.postValue(listOf<Category>(Category(1,"Food", "", emptyList<Product>())))
        }

        Espresso.onView(RecyclerViewMatcher(R.id.productsRV).atPosition(0))
            .check(ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Food"))))

    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        productViewModel = ProductViewModel(Application(), productUseCase)
        loadKoinModules(module {
            viewModel {
                productViewModel
            }
        })
    }

    @After
    fun cleanUp() {
        stopKoin()
    }
}