package brcomkassin.dungeonstranslator.manager;

import brcomkassin.dungeonstranslator.lang.Lang;
import brcomkassin.dungeonstranslator.exception.MissingTranslationException;
import brcomkassin.dungeonstranslator.lang.LangProvider;
import org.bukkit.configuration.file.FileConfiguration;

public class LangManager {

    public static String getMessage(Lang lang, String path) {
        FileConfiguration langConfig = LangProvider.get(lang);

        String message = langConfig.getString(path);

        if (message == null) throw new MissingTranslationException(lang, path);
        return message;
    }

}

