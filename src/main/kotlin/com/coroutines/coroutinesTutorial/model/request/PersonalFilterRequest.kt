package com.coroutines.coroutinesTutorial.model.request

data class PersonalFilterRequest (
    val idType : String,
    val pagingLimit : Int,
    val pagingOffset : Int
)