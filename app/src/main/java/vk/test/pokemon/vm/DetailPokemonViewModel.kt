package vk.test.pokemon.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vk.test.pokemon.data.PokemonRepository
import vk.test.pokemon.data.model.AbilityInfo
import vk.test.pokemon.data.model.PokemonAbility
import vk.test.pokemon.data.model.PokemonInfo

class DetailPokemonViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private var detailPokemonLiveData = MutableLiveData<PokemonInfo>()
    private var abilitiesLiveData = MutableLiveData<List<AbilityInfo>>()


    val detailPokemon
        get() = detailPokemonLiveData
    val abilities
        get() = abilitiesLiveData

    fun getDetailPokemon(id: String) {
        viewModelScope.launch {
            try {
                detailPokemonLiveData.postValue(repository.getDetailPokemon(id))
            } catch (e: Throwable) {
                Log.e("ERROR", "getDetailPokemon: ", e)
            }
        }
    }

    fun getAbilitiesInfo(abilities:List<PokemonAbility>) {
        val result = mutableListOf<AbilityInfo>()
        viewModelScope.launch {
            try {
                abilities.forEach {
                    result.add(repository.getAbilityInfo(it.ability.name))
                }
                abilitiesLiveData.postValue(result)
            }catch (e:Throwable){
                Log.e("ERROR", "getAbilityInfo: ", e)
            }
        }
    }


}