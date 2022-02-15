package br.com.intelbras.api

import br.com.intelbras.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IntelbrasAPI {

    @GET("pokemon")
    fun getPokemon(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): Call<MutableList<Pokemon>?>
}