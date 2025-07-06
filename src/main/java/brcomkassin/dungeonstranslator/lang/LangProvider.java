package brcomkassin.dungeonstranslator.lang;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.EnumMap;
import java.util.Map;

public class LangProvider {

    private static Map<Lang, FileConfiguration> configs = new EnumMap<>(Lang.class);

    public static FileConfiguration get(Lang lang) {
        return configs.get(lang);
    }

    public static void add(Lang lang, FileConfiguration config) {
        configs.put(lang, config);
    }
}
