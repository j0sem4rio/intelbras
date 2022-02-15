package br.com.intelbras.model.dao

import br.com.intelbras.model.Pokemon
import com.raizlabs.android.dbflow.sql.language.SQLite
import br.com.intelbras.model.dao.PokemonDao

class PokemonDao {
    val getPokemons: List<Pokemon>
        get() = SQLite.select().from(Pokemon::class.java)
            .queryList()

    companion object {
        private var dao: PokemonDao? = null
        val intance: PokemonDao?
            get() {
                if (dao == null) {
                    dao = PokemonDao()
                }
                return dao
            }
    }
}