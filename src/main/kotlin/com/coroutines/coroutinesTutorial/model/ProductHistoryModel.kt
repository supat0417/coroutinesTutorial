package com.coroutines.coroutinesTutorial.model

import java.time.LocalDate

data class ProductHistoryModel (
    val productId : Int,
    val productType: Int,
    val productExpr: LocalDate,
    val subProductId: Integer?
)