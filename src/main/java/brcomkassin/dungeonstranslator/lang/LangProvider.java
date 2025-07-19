package brcomkassin.dungeonstranslator.lang;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class LangProvider {

    private static Map<String, FileConfiguration> configs = new HashMap<>();

    public static FileConfiguration get(String plugin, Lang lang) {
        String path = plugin + ":" + lang;
        return configs.get(path);
    }

    public static void add(String plugin, Lang lang, FileConfiguration config) {
        String path = plugin + ":" + lang;
        configs.put(path, config);
    }

}
