package com.coroutines.coroutinesTutorial.model

import com.coroutines.coroutinesTutorial.model.response.Address
import java.time.LocalDate

data class PersonalModel (
    val id : Int,
    val firstName : String,
    val lastName : String,
    val birthDate : LocalDate,
    val province : String,
    val protalCode : String
)