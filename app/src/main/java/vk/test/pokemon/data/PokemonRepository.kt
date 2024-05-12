package vk.test.pokemon.data

import vk.test.pokemon.data.model.AbilityInfo
import vk.test.pokemon.data.model.Pokemon
import vk.test.pokemon.data.model.PokemonInfo
import vk.test.pokemon.data.networking.PokemonService

class PokemonRepository {

    private val service = PokemonService.create()

    suspend fun getPokemons(): List<Pokemon> {
        return service.getPokemons().results
    }

    suspend fun getDetailPokemon(id:String):PokemonInfo{
        return service.getDetailPokemon(id)
    }

    suspend fun getAbilityInfo(name:String): AbilityInfo{
        return service.getAbilityInfo(name)
    }
}