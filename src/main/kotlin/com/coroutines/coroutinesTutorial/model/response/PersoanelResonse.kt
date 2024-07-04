package com.coroutines.coroutinesTutorial.model.response

import java.time.LocalDate

data class PersoanelResonse (
    val id : Int,
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate,
    val age : Long,
    val address: Address

)
data class Address(
    val province: String,
    val protalCode : String
)