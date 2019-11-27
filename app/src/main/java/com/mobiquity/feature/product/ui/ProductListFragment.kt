package com.mobiquity.feature.product.ui

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.R
import com.mobiquity.feature.product.adapter.ProductListAdapter
import com.mobiquity.feature.product.dto.ExpandableCategory
import com.mobiquity.feature.product.viewmodel.ProductViewModel
import com.mobiquity.infrastructure.extensions.fault
import com.mobiquity.infrastructure.extensions.observe
import com.mobiquity.infrastructure.platform.BaseFragment
import com.mobiquity.infrastructure.platform.BaseUseCase
import kotlinx.android.synthetic.main.product_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProductListFragment : BaseFragment() {
    override fun interaction() {
        productsRV.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val mDividerItemDecoration = DividerItemDecoration(
            productsRV.context,
            RecyclerView.VERTICAL
        )
        mDividerItemDecoration.setDrawable(context?.getDrawable(R.drawable.list_divider)!!)
        productsRV.addItemDecoration(mDividerItemDecoration)
        productViewModel.run {
            observe(products)
            {
                showProgress(false, false)
                val adapter = ProductListAdapter(it.map {
                    ExpandableCategory(it.name, it.products)
                }) {
                    findNavController().navigate(ProductListFragmentDirections.toProductDetail(it))
                }
                productsRV.adapter = adapter
                adapter.expandAll()

            }
            fault(failure){
                handleFailure(it)
            }
        }
    }

    //region Layouts
    override val layoutResourceId = R.layout.product_list
    //endregion
    //region Injections
    private val productViewModel: ProductViewModel by sharedViewModel()

    //endregion

    //endregion
    //region Initializations
    override fun ignite(bundle: Bundle?) {
        if (productViewModel.products.value == null || (productViewModel.products.value as MutableList<*>).isEmpty()) {
            showProgress(true, true)
            productViewModel.fetchProducts(BaseUseCase.None())
        }
    }
}


