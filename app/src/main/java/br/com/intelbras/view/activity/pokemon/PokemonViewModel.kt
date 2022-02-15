package br.com.intelbras.view.activity.pokemon

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.intelbras.model.Pokemon


class PokemonViewModel() : ViewModel() {

    var mutableLiveData: MutableLiveData<List<Pokemon>>? = null

    var onMessageError: LiveData<Any>? = null

    private var fuelRepo: PokemonRepo? = null
    var offset: Int = 0
    var limit: Int = 100

    init {
        fuelRepo = PokemonRepo()
        mutableLiveData  = fuelRepo!!.mutableList
        onMessageError = fuelRepo!!._onMessageError
    }

    fun getPokemons(context: Context) {
        fuelRepo!!.getPokemons(context, limit, offset)
        offset += limit
    }

}