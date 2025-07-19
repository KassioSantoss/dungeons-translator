package brcomkassin.dungeonstranslator.prefix;

public class PrefixProvider {

    public static TranslatorPrefix getTranslatorPrefix(String prefix) {
        return new TranslatorPrefixImpl(prefix);
    }

}
