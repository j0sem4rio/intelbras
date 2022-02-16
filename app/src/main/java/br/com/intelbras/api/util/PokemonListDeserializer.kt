package br.com.intelbras.api.util


import br.com.intelbras.model.Pokemon
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.util.*

class PokemonListDeserializer : JsonDeserializer<List<Pokemon>> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): List<Pokemon> {
        val items: MutableList<Pokemon> = ArrayList()
        val jsonObject = json.asJsonObject
        val itemsJsonArray = jsonObject["results"].asJsonArray
        for (itemsJsonElement in itemsJsonArray) {
            val p = Pokemon()
            val itemJsonObject = itemsJsonElement.asJsonObject
            p.name = itemJsonObject["name"].asString
            p.url = itemJsonObject["url"].asString
            items.add(p)
        }
        return items
    }
}