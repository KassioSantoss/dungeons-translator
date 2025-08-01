package brcomkassin.dungeonstranslator;

import brcomkassin.dungeonstranslator.prefix.PrefixProvider;
import brcomkassin.dungeonstranslator.initializer.LangInitializer;
import brcomkassin.dungeonstranslator.lang.LangFileGenerator;
import brcomkassin.dungeonstranslator.prefix.TranslatorPrefix;
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
        langFileGenerator = new LangFileGenerator(this, getTranslatorPrefix());
        LangInitializer translatorInitializer = LangInitializer.of();
        translatorInitializer.init(getTranslatorPrefix(), langFileGenerator);
    }

    @Override
    public void onDisable() {

    }

    public static TranslatorPrefix getTranslatorPrefix() {
        return PrefixProvider.getTranslatorPrefix("DungeonsTranslator");
    }

}
