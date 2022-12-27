package com.example.retrofitsimple

import java.util.*

data class Post(
	val userId: Int,
	val id: Int,
	val title: String,
	val body: String
)