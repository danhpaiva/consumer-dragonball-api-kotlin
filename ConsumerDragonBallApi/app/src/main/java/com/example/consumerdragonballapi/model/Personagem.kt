package com.example.consumerdragonballapi.model

data class Personagem(
    val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val affiliation: String,
    val deletedAt: String?, // Pode ser nulo
    val originPlanet: OriginPlanet,
    val transformations: List<Transformation>
)
