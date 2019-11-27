package com.mobiquity.feature.product.viewholder

import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import com.mobiquity.BR
import com.mobiquity.databinding.ItemCategoryBinding
import com.mobiquity.feature.product.dto.ExpandableCategory
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import kotlinx.android.synthetic.main.item_category.view.*


class CategoryViewholder(val binding: ItemCategoryBinding): GroupViewHolder(binding.root) {

    fun bind(category: ExpandableCategory){
        binding.setVariable(BR.category, category)
        binding.executePendingBindings()
    }

    override fun expand() {
        animateExpand()
    }

    override fun collapse() {
        animateCollapse()
    }

    private fun animateExpand() {
        val rotate = RotateAnimation(360f, 180f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 300
        rotate.fillAfter = true
        itemView.arrow.animation = rotate
    }

    private fun animateCollapse() {
        val rotate = RotateAnimation(180f, 360f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 300
        rotate.fillAfter = true
        itemView.arrow.animation = rotate
    }
}