package brcomkassin.dungeonstranslator.lang;

import org.bukkit.entity.Player;

public class LangDetector {

    public static Lang detectLanguage(Player player) {
        return Lang.fromCode(player.locale().getLanguage());
    }

}
