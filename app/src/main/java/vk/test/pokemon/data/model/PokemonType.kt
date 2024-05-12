package vk.test.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class PokemonType(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: Type,
)
