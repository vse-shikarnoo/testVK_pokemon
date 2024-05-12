package vk.test.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class PokemonAbility(
    @SerializedName("slot")
    val slot: String,
    @SerializedName("ability")
    val ability: Ability,
)
