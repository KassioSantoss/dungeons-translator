package brcomkassin.dungeonstranslator.utils;

import com.google.gson.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

import java.lang.reflect.Type;

public class KComponentAdapter implements JsonSerializer<Component>, JsonDeserializer<Component> {
    public static final KComponentAdapter INSTANCE = new KComponentAdapter();

    private KComponentAdapter() {}

    @Override
    public Component deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return GsonComponentSerializer.gson().deserializeFromTree(json);
    }

    @Override
    public JsonElement serialize(Component src, Type typeOfSrc, JsonSerializationContext context) {
        return GsonComponentSerializer.gson().serializeToTree(src);
    }

}
