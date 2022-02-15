package br.com.intelbras.view.activity.pokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.recyclerview.widget.RecyclerView
import br.com.intelbras.BR
import br.com.intelbras.R
import br.com.intelbras.databinding.ItemPokemonBinding
import br.com.intelbras.model.Pokemon


class PokemonAdapter(val callback: PokemonClick) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    var pokemonList: MutableList<Pokemon>

    init {
        pokemonList = ArrayList()
    }

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    fun addData(arrList: List<Pokemon>){
        this.pokemonList.addAll(arrList)
        _isEmptyList.value = pokemonList.isNullOrEmpty()
    }

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int):  ViewHolder {
        val binding: ItemPokemonBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_pokemon, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.pokemon, data)
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(pokemonList.get(pos))
        holder.binding.also {
            it.pokemon = pokemonList[pos]
            it.pokemonCallback = callback
        }
    }
}