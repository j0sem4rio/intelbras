package br.com.intelbras.model

import br.com.intelbras.model.dao.IntelDB
import com.google.gson.annotations.Expose
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = IntelDB::class)
class Pokemon : BaseModel() {
    @Column(name = "id")
    @PrimaryKey
    var id: Long? = null

    @Expose
    @Column(name = "name")
    var name: String? = null

    @Expose
    @Column(name = "url")
    var url: String? = null

    fun idPokemon(): String{
        return  url!!.substringAfter("https://pokeapi.co/api/v2/pokemon/").substringBefore('/')
    }
}