package br.com.intelbras.view.activity.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.intelbras.model.PokemonItem


class PokemonDetailsViewModel() : ViewModel() {

//    var mutableLiveData: MutableLiveData<PokemonItem>? = null

    private val _pokemon = MutableLiveData<PokemonItem>().apply { value = null }
    val pokemon: LiveData<PokemonItem> = _pokemon

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private var pokemonDetailsRepo: PokemonDetailsRepo? = null

    init {
        pokemonDetailsRepo = PokemonDetailsRepo()

    }

    fun getDetailPokemon(context: Context, id: Int) {
        pokemonDetailsRepo?.getDetailPokemon(object : OperationCallback<PokemonItem> {
            override fun onError(error: String?) {

                _onMessageError.value = error
            }

            override fun onSuccess(data: PokemonItem?) {

                _pokemon.value = data

            }
        },context ,id)
    }

}