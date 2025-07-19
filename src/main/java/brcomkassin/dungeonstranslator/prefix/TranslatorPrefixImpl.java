package brcomkassin.dungeonstranslator.prefix;

public record TranslatorPrefixImpl(String prefix) implements TranslatorPrefix {
    @Override
    public String getPrefix() {
        return prefix;
    }
}
