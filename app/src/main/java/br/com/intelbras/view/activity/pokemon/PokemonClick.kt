package br.com.intelbras.view.activity.pokemon

import br.com.intelbras.model.Pokemon


class PokemonClick(val block: (Pokemon) -> Unit) {
    /**
     * Called when a Pokemon is clicked
     *
     * @param Pokemon the Pokemon that was clicked
     */
    fun onClick(pokemon: Pokemon) = block(pokemon)
}