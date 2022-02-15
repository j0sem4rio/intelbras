package br.com.intelbras.task

import android.content.Context
import android.os.Handler
import br.com.intelbras.R
import br.com.intelbras.api.PokemonAPI
import br.com.intelbras.api.util.ResponseWrapper
import br.com.intelbras.model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPokemonsThreads(private val handler: Handler, private val context: Context, private val  limit: Int? = 100, private val offset: Int) : BaseThreads(), Runnable {
    override fun run() {
        val myMsg = handler.obtainMessage()
        val callback: Callback<MutableList<Pokemon>?> = object : Callback<MutableList<Pokemon>?> {
            override fun onResponse(call: Call<MutableList<Pokemon>?>, response: Response<MutableList<Pokemon>?>) {
                if (response.isSuccessful) {
                    myMsg.obj = response.body()
                    myMsg.what = ResponseWrapper.ID_SUCCESS
                } else {
                    myMsg.what = ResponseWrapper.ID_FAILURE
                    myMsg.obj = context.getString(R.string.onFailure)
                }
                handler.sendMessage(myMsg)
            }

            override fun onFailure(call: Call<MutableList<Pokemon>?>, t: Throwable) {
                myMsg.what = ResponseWrapper.ID_FAILURE
                myMsg.obj = context.getString(R.string.onFailure)
                handler.sendMessage(myMsg)
                fail(t.toString())
            }
        }
        PokemonAPI.getPokemon(callback, limit, offset)
    }
}