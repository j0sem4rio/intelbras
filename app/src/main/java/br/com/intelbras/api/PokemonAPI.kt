package br.com.intelbras.api

import br.com.intelbras.model.Pokemon
import retrofit2.Call
import retrofit2.Callback

object PokemonAPI {

    fun getPokemon(callback: Callback<MutableList<Pokemon>?>, limit: Int?, offset: Int) {
        val growlerAPI = IntelbrasApiServer.apiServer
        val beerContentCall: Call<MutableList<Pokemon>?>?
        beerContentCall =  growlerAPI!!.getPokemon(limit, offset)

        beerContentCall.enqueue(callback)
    }
//
//    fun getBeerDetails(callback: Callback<Beer?>, id: Long?) {
//        val growlerAPI = GrowlerApiServer.apiServer
//        val beerContentCall: Call<Beer?>?
//        beerContentCall = growlerAPI!!.getBeersDetails(id)
//        beerContentCall.enqueue(callback)
//    }

}