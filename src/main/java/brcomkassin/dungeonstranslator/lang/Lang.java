package brcomkassin.dungeonstranslator.lang;

import lombok.Getter;

@Getter
public enum Lang {
    PT("pt_br"),
    EN("en_us"),
    ES("es_es");

    private final String localeCode;

    Lang(String localeCode) {
        this.localeCode = localeCode;
    }

    public static Lang fromLocale(String rawLocale) {
        String lower = rawLocale.toLowerCase();
        for (Lang lang : values()) {
            if (lang.localeCode.equalsIgnoreCase(lower)) {
                return lang;
            }
        }
        return EN;
    }

    public static Lang fromCode(String langCode) {
        return switch (langCode.toLowerCase()) {
            case "pt", "pt_br" -> PT;
            case "es", "es_es" -> ES;
            case "en", "en_us" -> EN;
            default -> EN;
        };
    }

}
