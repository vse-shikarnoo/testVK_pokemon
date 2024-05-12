package vk.test.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
) {
    val id: String
        get() = url.split("/").dropLast(1).last()
}
