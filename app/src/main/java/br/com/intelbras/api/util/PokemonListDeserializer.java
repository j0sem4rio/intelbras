package br.com.intelbras.api.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.com.intelbras.model.Pokemon;


public class PokemonListDeserializer implements JsonDeserializer<List<Pokemon>> {
    @Override
    public List<Pokemon> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Pokemon> items = new ArrayList<>();

        final JsonObject jsonObject = json.getAsJsonObject();
        final JsonArray itemsJsonArray = jsonObject.get("results").getAsJsonArray();

        for (JsonElement itemsJsonElement : itemsJsonArray) {
            Pokemon p = new Pokemon();
            final JsonObject itemJsonObject = itemsJsonElement.getAsJsonObject();
            p.setName(itemJsonObject.get("name").getAsString());
            p.setUrl(itemJsonObject.get("url").getAsString());

            items.add(p);
        }

        return items;
    }
}
