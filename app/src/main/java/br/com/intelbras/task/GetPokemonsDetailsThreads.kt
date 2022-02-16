package br.com.intelbras.task

import android.content.Context
import android.os.Handler
import br.com.intelbras.IntelbrasApplication
import br.com.intelbras.R
import br.com.intelbras.api.PokemonAPI
import br.com.intelbras.api.util.ResponseWrapper
import br.com.intelbras.model.Pokemon
import br.com.intelbras.model.PokemonItem
import br.com.intelbras.view.activity.detail.OperationCallback
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.SQLite
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPokemonsDetailsThreads(private val callback: OperationCallback<PokemonItem>, private val context: Context, private val  id: Int) : BaseThreads(), Runnable {
    override fun run() {
        if (IntelbrasApplication.instance!!.isNetworkAvailable) {
            val callback: Callback<PokemonItem?> =
                object : Callback<PokemonItem?> {
                    override fun onResponse(
                        call: Call<PokemonItem?>,
                        response: Response<PokemonItem?>
                    ) {
                        if (response.isSuccessful) {
                            callback.onSuccess(response.body())
                        } else {
                            callback.onError(context.getString(R.string.onFailure))
                        }
                    }

                    override fun onFailure(call: Call<PokemonItem?>, t: Throwable) {
                        callback.onError(context.getString(R.string.onFailure))
                        fail(t.toString())
                    }
                }
            PokemonAPI.getPokemonDetails(callback, id)
        } else {
            callback.onError(context.getString(R.string.onFailure))
        }
    }
}