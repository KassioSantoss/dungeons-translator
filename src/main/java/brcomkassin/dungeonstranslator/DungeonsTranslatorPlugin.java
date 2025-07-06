package brcomkassin.dungeonstranslator;

import brcomkassin.dungeonstranslator.initializer.LangInitializer;
import brcomkassin.dungeonstranslator.lang.LangFileGenerator;
import brcomkassin.dungeonstranslator.utils.KColoredLogger;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class DungeonsTranslatorPlugin extends JavaPlugin {

    @Getter
    private static DungeonsTranslatorPlugin instance;
    @Getter
    private LangFileGenerator langFileGenerator;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        KColoredLogger.info("&6Carregando DungeonsTranslator...");
        langFileGenerator = new LangFileGenerator(this);
        LangInitializer translatorInitializer = LangInitializer.of();
        translatorInitializer.init(langFileGenerator);
        KColoredLogger.info("&6DungeonsTranslator carregado!");
    }

    @Override
    public void onDisable() {

    }

}
