package vk.test.pokemon.data.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import vk.test.pokemon.BuildConfig
import vk.test.pokemon.data.model.AbilityInfo
import vk.test.pokemon.data.model.PokemonInfo


interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemons():PokemonSearchResponse

    @GET("pokemon/{id}")
    suspend fun getDetailPokemon(
        @Path("id") id:String
    ):PokemonInfo

    @GET("ability/{name}")
    suspend fun getAbilityInfo(
        @Path("name") name:String
    ):AbilityInfo

    companion object{
        fun create(): PokemonService {
            val okHttpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(PokemonService::class.java)
        }
    }
}