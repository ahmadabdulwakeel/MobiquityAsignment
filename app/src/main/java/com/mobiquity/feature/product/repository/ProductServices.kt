package com.mobiquity.feature.product.repository

import com.mobiquity.BuildConfig
import com.mobiquity.data.tables.Category
import retrofit2.Call
import retrofit2.http.GET

interface ProductServices {
    @GET(BuildConfig.BASE_URL)
     fun fetchProducts(): Call<List<Category>>

}