package brcomkassin.dungeonstranslator.api;

import brcomkassin.dungeonstranslator.prefix.TranslatorPrefix;
import brcomkassin.dungeonstranslator.initializer.LangInitializer;
import brcomkassin.dungeonstranslator.lang.Lang;
import brcomkassin.dungeonstranslator.lang.LangDetector;
import brcomkassin.dungeonstranslator.lang.LangFileGenerator;
import brcomkassin.dungeonstranslator.manager.LangManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

/**
 * API principal de tradução utilizada no plugin.
 * Permite buscar mensagens de linguagem para jogadores ou por idioma direto.
 */
public class TranslatorAPI {

    /**
     * Inicializa o sistema de tradução, gerando os arquivos e carregando as configurações.
     *
     * @param plugin A instância principal do plugin.
     */
    public static void init(JavaPlugin plugin, TranslatorPrefix translatorPrefix) {
        LangFileGenerator fileGenerator = new LangFileGenerator(plugin, translatorPrefix);
        LangInitializer translatorInitializer = LangInitializer.of();
        translatorInitializer.init(translatorPrefix, fileGenerator);
    }

    /**
     * Retorna uma mensagem com base em um idioma específico.
     *
     * @param lang Idioma desejado.
     * @param path Caminho no arquivo de idioma.
     * @return Mensagem localizada.
     */
    public static String getMessage(TranslatorPrefix translatorPrefix, Lang lang, String path) {
        return LangManager.getMessage(translatorPrefix.getPrefix(), lang, path);
    }

    /**
     * Retorna uma mensagem com base na linguagem detectada do jogador.
     *
     * @param player Jogador alvo.
     * @param path   Caminho no arquivo de idioma.
     * @return Mensagem localizada.
     */
    public static String getMessage(TranslatorPrefix translatorPrefix, Player player, String path) {
        Lang lang = LangDetector.detectLanguage(player);
        return LangManager.getMessage(translatorPrefix.getPrefix(), lang, path);
    }

    /**
     * Retorna uma mensagem com placeholders substituídos, usando a linguagem do jogador.
     *
     * @param player       Jogador alvo.
     * @param path         Caminho da mensagem.
     * @param placeholders Mapa de %chave% -> valor.
     * @return Mensagem formatada com os dados inseridos.
     */
    public static String getMessage(TranslatorPrefix translatorPrefix, Player player, String path, Map<String, String> placeholders) {
        Lang lang = LangDetector.detectLanguage(player);
        return LangManager.getMessage(translatorPrefix.getPrefix(), lang, path, placeholders);
    }
}
