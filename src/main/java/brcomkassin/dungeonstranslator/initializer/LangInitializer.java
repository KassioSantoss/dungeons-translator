package brcomkassin.dungeonstranslator.initializer;

import brcomkassin.dungeonstranslator.DungeonsTranslatorPlugin;
import brcomkassin.dungeonstranslator.prefix.TranslatorPrefix;
import brcomkassin.dungeonstranslator.lang.Lang;
import brcomkassin.dungeonstranslator.lang.LangFileGenerator;
import brcomkassin.dungeonstranslator.lang.LangProvider;
import brcomkassin.dungeonstranslator.utils.KColoredLogger;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Set;

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

    public void init(TranslatorPrefix prefix, LangFileGenerator fileGenerator) {
        KColoredLogger.info("&6Iniciando o sistema de tradução para o plugin: " + prefix);

        config_pt = fileGenerator.get(prefix, Lang.PT);
        config_en = fileGenerator.get(prefix, Lang.EN);
        config_es = fileGenerator.get(prefix, Lang.ES);

        loadLanguage(prefix, Lang.PT, config_pt);
        loadLanguage(prefix, Lang.EN, config_en);
        loadLanguage(prefix, Lang.ES, config_es);
        enable();
        KColoredLogger.info("&aSistema de tradução iniciado com sucesso!");
    }

    private void loadLanguage(TranslatorPrefix prefix, Lang lang, FileConfiguration config) {
        KColoredLogger.info("Carregando arquivo para o idioma: " + lang.name());
        KColoredLogger.info("Caminho do arquivo: " + config.getName());
        KColoredLogger.info("Caminho do arquivo: " + config);

        Set<String> keys = config.getKeys(true);
        KColoredLogger.info("Chaves encontradas no arquivo: " + keys);

        if (!keys.contains("event.join-server")) {
            KColoredLogger.error("AVISO: A chave 'event.join-server' NÃO foi encontrada após carregar o arquivo!");
        }
        LangProvider.add(prefix.getPrefix(), lang, config);
        KColoredLogger.info("PREFIX: " + prefix.getPrefix() + " - IDIOMA: " + lang.name());
    }

    private void enable() {

    }

    private void disable() {

    }

}
