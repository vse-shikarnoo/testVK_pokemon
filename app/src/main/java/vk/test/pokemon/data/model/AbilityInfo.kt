package vk.test.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class AbilityInfo(
    @SerializedName("effect_entries")
    val effectEntries: List<EffectEntry>,
    @SerializedName("name")
    val name: String,
)
