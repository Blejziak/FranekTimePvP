package dev.franek.timepvp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.md_5.bungee.api.ChatColor;

public final class TextUtil {

    private static final Pattern HEX_PATTERN = Pattern.compile("(#[a-fA-F0-9]{6})");
    public static final String WATERMARK = "made by franekk";

    private TextUtil() {
    }

    public static String color(String text) {
        if (text == null) {
            return "";
        }
        Matcher matcher = HEX_PATTERN.matcher(text);
        while (matcher.find()) {
            String hex = text.substring(matcher.start(), matcher.end());
            text = text.replace(hex, "" + ChatColor.of(hex));
            matcher = HEX_PATTERN.matcher(text);
        }
        return ChatColor.translateAlternateColorCodes('&', text).replace(">>", "\u00bb");
    }
}
