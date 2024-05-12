package vk.test.pokemon.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vk.test.pokemon.data.PokemonRepository
import vk.test.pokemon.data.model.Pokemon

class PokemonViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private var pokemonsListLiveData = MutableLiveData<List<Pokemon>>()
    private var errorLiveData = MutableLiveData<Unit>()
    private var loadLiveData = MutableLiveData<Boolean>()

    val pokemonsList
        get() = pokemonsListLiveData

    val errorState
        get() = errorLiveData

    val loadState
        get() = loadLiveData

    fun getPokemons() {
        viewModelScope.launch {
            try {
                loadLiveData.postValue(true)
                pokemonsListLiveData.postValue(repository.getPokemons())
            } catch (e: Throwable) {
                Log.e("ERROR", "getPokemons: ", e)
                errorLiveData.postValue(Unit)
            } finally {
                loadLiveData.postValue(false)
            }
        }
    }


}