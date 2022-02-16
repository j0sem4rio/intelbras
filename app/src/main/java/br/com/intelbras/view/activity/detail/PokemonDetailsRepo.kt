package br.com.intelbras.view.activity.detail

import android.content.Context
import br.com.intelbras.model.PokemonItem
import br.com.intelbras.task.GetPokemonsDetailsThreads


class PokemonDetailsRepo {

    val TAG = javaClass.simpleName


    fun getDetailPokemon(callback: OperationCallback<PokemonItem>, context: Context, id: Int) {

        val pokemonsDetailsThreads= GetPokemonsDetailsThreads(callback, context, id)
        Thread(pokemonsDetailsThreads).start()

    }

}