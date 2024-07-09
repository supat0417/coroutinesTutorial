package com.coroutines.coroutinesTutorial.model.response

import java.time.LocalDate

data class ProductFilterResponse (
    val productId: Int,
    val productType: Int,
    val productName: String,
    val subProductId: Integer?,
    val subProductType: Int?,
    val subProductName: String?,
    val productEx: LocalDate
)