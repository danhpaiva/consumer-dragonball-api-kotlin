package com.example.consumerdragonballapi.model

data class Transformation(
    val id: Int,
    val name: String,
    val image: String,
    val ki: String,
    val deletedAt: String? // Pode ser nulo
)
