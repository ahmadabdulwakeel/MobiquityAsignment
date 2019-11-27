package com.mobiquity.feature.product.adapter

import android.view.ViewGroup
import com.mobiquity.R
import com.mobiquity.data.tables.Product
import com.mobiquity.feature.product.dto.ExpandableCategory
import com.mobiquity.feature.product.viewholder.CategoryViewholder
import com.mobiquity.feature.product.viewholder.ProductViewholder
import com.mobiquity.infrastructure.extensions.dataBind
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class ProductListAdapter( list: List<ExpandableGroup<*>>, val productClickListener: (Product)->Unit): ExpandableRecyclerViewAdapter<CategoryViewholder, ProductViewholder>(list) {
    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): CategoryViewholder {
        return CategoryViewholder(parent.dataBind(R.layout.item_category))
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): ProductViewholder {
        return ProductViewholder(parent.dataBind(R.layout.item_product), productClickListener )
    }

    override fun onBindChildViewHolder(
        holder: ProductViewholder,
        flatPosition: Int,
        group: ExpandableGroup<*>,
        childIndex: Int
    ) {
        holder.bind(group.items[childIndex] as Product)
    }

    override fun onBindGroupViewHolder(
        holder: CategoryViewholder,
        flatPosition: Int,
        group: ExpandableGroup<*>
    ) {
        holder.bind(group as ExpandableCategory)
    }

    fun expandAll() {
        expandableList.groups.forEach {
            val position = expandableList.getFlattenedGroupIndex(it)
            if (!isGroupExpanded(position)) {
                toggleGroup(position)
            }
        }
    }
}