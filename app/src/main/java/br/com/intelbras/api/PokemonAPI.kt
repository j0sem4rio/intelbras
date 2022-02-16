package br.com.intelbras.api

import br.com.intelbras.model.Pokemon
import br.com.intelbras.model.PokemonItem
import retrofit2.Call
import retrofit2.Callback

object PokemonAPI {

    fun getPokemon(callback: Callback<MutableList<Pokemon>?>, limit: Int?, offset: Int) {
        val growlerAPI = IntelbrasApiServer.apiServer
        val beerContentCall: Call<MutableList<Pokemon>?>?
        beerContentCall =  growlerAPI!!.getPokemon(limit, offset)

        beerContentCall.enqueue(callback)
    }

    fun getPokemonDetails(callback: Callback<PokemonItem?>, id: Int?) {
        val growlerAPI = IntelbrasApiServer.apiServer
        val beerContentCall: Call<PokemonItem?>?
        beerContentCall = growlerAPI!!.getDetailsPokemon(id)
        beerContentCall.enqueue(callback)
    }

}