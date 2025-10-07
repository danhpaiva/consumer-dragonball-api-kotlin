package com.example.consumerdragonballapi.model

data class OriginPlanet(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String,
    val deletedAt: String? // Pode ser nulo
)
