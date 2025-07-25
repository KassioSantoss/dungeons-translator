package brcomkassin.dungeonstranslator.manager;

import brcomkassin.dungeonstranslator.DungeonsTranslatorPlugin;
import brcomkassin.dungeonstranslator.lang.Lang;
import brcomkassin.dungeonstranslator.lang.LangProvider;
import brcomkassin.dungeonstranslator.utils.KColoredLogger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.checkerframework.checker.units.qual.K;

import java.util.List;
import java.util.Map;

/**
 * Responsável por recuperar e formatar mensagens de linguagem
 * baseadas no idioma (Lang) atual do jogador ou sistema.
 */
public class LangManager {

    /**
     * Retorna uma mensagem baseada no idioma e caminho fornecidos.
     * Suporta mensagens simples ou multilinhas (lista no YAML).
     *
     * @param lang Idioma desejado (PT, EN, ES, etc.)
     * @param path Caminho da mensagem no arquivo YAML (ex: "weapon-command.usage")
     * @return A mensagem formatada, ou uma mensagem de erro se não encontrada.
     */
    public static String getMessage(String prefix, Lang lang, String path) {
        FileConfiguration langConfig = LangProvider.get(prefix, lang);
        if (langConfig == null) {
            return "§c[Lang] Arquivo de linguagem não carregado: " + lang.name();
        }
        String message = langConfig.getString(path);
        return message != null ? message : "§c[Lang] Mensagem não encontrada: " + path;
    }

    /**
     * Versão da mensagem com placeholders substituíveis.
     *
     * @param lang         Idioma desejado.
     * @param path         Caminho da mensagem.
     * @param placeholders Mapa de placeholders no formato: "chave" -> "valor"
     * @return Mensagem final formatada.
     */
    public static String getMessage(String prefix, Lang lang, String path, Map<String, String> placeholders) {
        String message = getMessage(prefix, lang, path);
        return format(message, placeholders);
    }

    /**
     * Substitui todos os placeholders no formato %chave% por seus valores.
     *
     * @param message      Texto base com placeholders.
     * @param placeholders Mapa com valores a substituir.
     * @return Mensagem formatada.
     */
    public static String format(String message, Map<String, String> placeholders) {
        if (placeholders == null || placeholders.isEmpty()) return message;

        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            message = message.replace("%" + entry.getKey() + "%", entry.getValue());
        }

        return message;
    }
}
