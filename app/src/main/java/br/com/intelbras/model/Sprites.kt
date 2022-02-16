package br.com.intelbras.model

import com.google.gson.annotations.Expose

class Sprites {

    @Expose
    var front_shiny_female : String? = null

    @Expose
    var front_shiny: String? = null

    @Expose
    var front_female: String? = null

    @Expose
    var front_default: String? = null

    @Expose
    var back_shiny_female: String? = null

    @Expose
    var back_shiny: String? = null

    @Expose
    var back_female: String? = null

    @Expose
    var back_default: String? = null

    @Expose
    var other: Other? = null
}