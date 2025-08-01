package brcomkassin.dungeonstranslator.lang;

import brcomkassin.dungeonstranslator.prefix.TranslatorPrefix;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LangFileGenerator {

    private final JavaPlugin plugin;
    private final Map<String, FileConfiguration> configs = new HashMap<>();

    public LangFileGenerator(JavaPlugin plugin, TranslatorPrefix translatorPrefix) {
        this.plugin = plugin;
        generateLangFiles(translatorPrefix);
    }

    private void generateLangFiles(TranslatorPrefix translatorPrefix) {
        File langFolder = new File(plugin.getDataFolder(), "lang");
        if (!langFolder.exists() && !langFolder.mkdirs()) {
            plugin.getLogger().severe("Não foi possível criar a pasta 'lang/'");
            return;
        }

        for (Lang lang : Lang.values()) {
            File file = new File(langFolder, lang.name().toLowerCase() + ".yml");

            if (file.exists()) {
                configs.put(translatorPrefix.getPrefix() + ":" + lang, YamlConfiguration.loadConfiguration(file));
                continue;
            }

            YamlConfiguration config = new YamlConfiguration();

            try {
                config.save(file);
                plugin.getLogger().info("Arquivo criado: " + file.getName());
                configs.put(translatorPrefix.getPrefix() + ":" + lang, config);
            } catch (IOException e) {
                plugin.getLogger().severe("Erro ao salvar " + file.getName() + ": " + e.getMessage());
            }
        }
    }

    public FileConfiguration get(TranslatorPrefix translatorPrefix, Lang lang) {
        return configs.get(translatorPrefix.getPrefix() + ":" + lang);
    }

}
