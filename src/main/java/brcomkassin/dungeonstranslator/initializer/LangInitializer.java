package brcomkassin.dungeonstranslator.initializer;

import brcomkassin.dungeonstranslator.DungeonsTranslatorPlugin;
import brcomkassin.dungeonstranslator.lang.Lang;
import brcomkassin.dungeonstranslator.lang.LangFileGenerator;
import brcomkassin.dungeonstranslator.lang.LangProvider;
import org.bukkit.configuration.file.FileConfiguration;

public class LangInitializer {

    private final DungeonsTranslatorPlugin plugin = DungeonsTranslatorPlugin.getInstance();
    private FileConfiguration config_pt;
    private FileConfiguration config_en;
    private FileConfiguration config_es;

    private final static LangInitializer INSTANCE = new LangInitializer();

    private LangInitializer() {
    }

    public static LangInitializer of() {
        return INSTANCE;
    }

    public void init(LangFileGenerator fileGenerator) {
        config_pt = fileGenerator.get(Lang.PT);
        config_en = fileGenerator.get(Lang.EN);
        config_es = fileGenerator.get(Lang.ES);

        LangProvider.add(Lang.PT, config_pt);
        LangProvider.add(Lang.EN, config_en);
        LangProvider.add(Lang.ES, config_es);
        enable();
    }

    private void enable() {

    }

    private void disable() {

    }

}
