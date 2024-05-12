package vk.test.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class PokemonInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("stats")
    var stats: List<PokemonStat>,
    @SerializedName("abilities")
    val abilities: List<PokemonAbility>,
)
