package brcomkassin.dungeonstranslator.lang;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class LangFileGenerator {

    private final JavaPlugin plugin;
    private final Map<Lang, FileConfiguration> configs = new EnumMap<>(Lang.class);

    public LangFileGenerator(JavaPlugin plugin) {
        this.plugin = plugin;
        generateLangFiles();
    }

    private void generateLangFiles() {
        File langFolder = new File(plugin.getDataFolder(), "lang");
        if (!langFolder.exists() && !langFolder.mkdirs()) {
            plugin.getLogger().severe("Não foi possível criar a pasta 'lang/'");
            return;
        }

        for (Lang lang : Lang.values()) {
            File file = new File(langFolder, lang.name().toLowerCase() + ".yml");

            if (file.exists()) {
                configs.put(lang, YamlConfiguration.loadConfiguration(file));
                continue;
            }

            YamlConfiguration config = new YamlConfiguration();
            config.createSection(lang.getRootKey());

            try {
                config.save(file);
                plugin.getLogger().info("Arquivo criado: " + file.getName());
                configs.put(lang, config);
            } catch (IOException e) {
                plugin.getLogger().severe("Erro ao salvar " + file.getName() + ": " + e.getMessage());
            }
        }
    }

    public FileConfiguration get(Lang lang) {
        return configs.get(lang);
    }
}
