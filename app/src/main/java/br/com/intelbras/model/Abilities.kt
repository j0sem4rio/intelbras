package br.com.intelbras.model

import com.google.gson.annotations.Expose

class Abilities {

    @Expose
    var is_hidden : Boolean? = false

    @Expose
    var slot: Int?  = null

    @Expose
    var ability: Ability?  = null
}