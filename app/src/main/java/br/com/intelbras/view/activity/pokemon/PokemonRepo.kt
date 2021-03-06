package br.com.intelbras.view.activity.pokemon

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import br.com.intelbras.api.util.ResponseWrapper
import br.com.intelbras.model.Pokemon
import br.com.intelbras.model.dao.PokemonDao
import br.com.intelbras.task.GetPokemonsThreads
import java.lang.IllegalStateException
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.SQLite


class PokemonRepo {

    val TAG = javaClass.simpleName
    var mutableList: MutableLiveData<List<Pokemon>> = MutableLiveData()
    val _onMessageError = MutableLiveData<Any>()

    fun getPokemons(context: Context, limit: Int, offset: Int) {

        val pokemonsThreads = GetPokemonsThreads(handler, context, limit, offset)
        Thread(pokemonsThreads).start()

    }

    var handler = Handler(Looper.getMainLooper()) { msg ->
        when (msg.what) {
            ResponseWrapper.ID_FAILURE ->{
                mutableList.value = emptyList()
            }
            ResponseWrapper.ID_SUCCESS -> {
                val pokemon: List<Pokemon> = msg.obj as List<Pokemon>
                FlowManager.getModelAdapter(Pokemon::class.java).saveAll(pokemon)
                mutableList.value = pokemon

            }
            ResponseWrapper.ID_OFFLINE -> {
                mutableList.value =  PokemonDao.intance?.getPokemons
            }
            else -> throw IllegalStateException("Unexpected value: " + msg.what)
        }
        false
    }

}