package com.mobiquity.feature.product.viewholder

import com.mobiquity.BR
import com.mobiquity.data.tables.Product
import com.mobiquity.databinding.ItemProductBinding
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder

class ProductViewholder(val binding: ItemProductBinding, productClickListener: (Product)->Unit): ChildViewHolder(binding.root) {
    init {
        itemView.setOnClickListener {
            productClickListener.invoke(binding.product!!)
        }
    }
    fun bind(product: Product){
        binding.setVariable(BR.product, product)
        binding.executePendingBindings()
    }
}