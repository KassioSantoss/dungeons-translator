package brcomkassin.dungeonstranslator.exception;

import brcomkassin.dungeonstranslator.lang.Lang;

public class MissingTranslationException extends RuntimeException {
    public MissingTranslationException(Lang lang, String path) {
        super("Tradução não encontrada para '" + path + "' no idioma '" + lang.name() + "'");
    }
}
