package com.mobiquity.feature.product.ui

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.mobiquity.BR
import com.mobiquity.R
import com.mobiquity.infrastructure.platform.BaseFragment


class ProductDetailFragment : BaseFragment() {
    override var shouldBindData = true

    val args: ProductListFragmentArgs by navArgs()
    override fun interaction() {
        binding?.let {
            it.setVariable(BR.product, args.product)
            it.executePendingBindings()
        }

    }

    //region Layouts
    override val layoutResourceId = R.layout.product_detail
    //endregion

    //region Initializations
    override fun ignite(bundle: Bundle?) {
    }

}


