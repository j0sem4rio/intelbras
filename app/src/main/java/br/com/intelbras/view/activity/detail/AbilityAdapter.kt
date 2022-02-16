package br.com.intelbras.view.activity.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.intelbras.BR
import br.com.intelbras.R
import br.com.intelbras.databinding.ItemAbilityBinding
import br.com.intelbras.model.Abilities

class AbilityAdapter (private var abilities: List<Abilities>) :  RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int):ViewHolder {
        val binding: ItemAbilityBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_ability, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return abilities.size
    }

    fun update(data: List<Abilities>) {
        abilities = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(abilities[pos])
        holder.binding.also {
            it.ability = abilities[pos]
        }
    }

    class ViewHolder(val binding: ItemAbilityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.pokemon, data)
            binding.executePendingBindings()
        }
    }

}