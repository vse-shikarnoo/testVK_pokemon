package vk.test.pokemon.data.networking

import com.google.gson.annotations.SerializedName
import vk.test.pokemon.data.model.Pokemon

data class PokemonSearchResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val results: List<Pokemon>,
)
