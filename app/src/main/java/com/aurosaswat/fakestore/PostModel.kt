package com.aurosaswat.fakestore

data class PostModel(
    val id:Int?=null,
    val title:String?=null,
    val price:Double?=null,
    val description: String?=null,
    val category: String?=null,
    val image:String?=null,
    val rating:Rating?=null
)


