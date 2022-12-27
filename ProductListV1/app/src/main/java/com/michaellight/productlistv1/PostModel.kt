package com.michaellight.productlistv1

data class PostModel(
	val id: Int? = null,
	val title: String? = null,
	val description: String? = null,
	val price: Int? = null,
	val discountPercentage: Double? = null,
	val rating: Double? = null,
	val stock: Int? = null,
	val brand: String? = null,
	val category: String? = null,
	val thumbnail: String? = null,
	val images: String? = null
)