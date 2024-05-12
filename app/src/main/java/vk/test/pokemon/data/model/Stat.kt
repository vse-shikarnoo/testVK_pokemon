package vk.test.pokemon.data.model

import com.google.gson.annotations.SerializedName
import vk.test.pokemon.R


data class Stat(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
) {
    val img: Int
        get() = when (name) {
            "hp" -> {
                R.drawable.hp_img
            }

            "attack" -> {
                R.drawable.attack_img
            }

            "defense" -> {
                R.drawable.defense_img
            }

            "speed" -> {
                R.drawable.speed_img
            }
            else -> {
                0
            }
        }
}
