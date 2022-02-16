package br.com.intelbras.model

import com.google.gson.annotations.Expose

class PokemonItem {

    @Expose
    var species: Pokemon? = null

    @Expose
    var sprites: Sprites? = null

    @Expose
    var height: Int? = null

    @Expose
    var weight: Int? = null

}