package dev.franek.timepvp.settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class TimeSettings {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    public Options options = new Options();

    public static TimeSettings load(Path file) {
        TimeSettings settings = null;
        if (Files.exists(file)) {
            try {
                String json = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
                settings = GSON.fromJson(json, TimeSettings.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (settings == null || settings.options == null) {
            settings = new TimeSettings();
        }
        return settings;
    }

    public static void save(TimeSettings settings, Path file) {
        try {
            Files.createDirectories(file.getParent());
            Files.write(file, GSON.toJson(settings).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final class Options {
        public int pvpTimeSeconds = 72000;
        public String cantAttackMessage = "&cNie mozesz atakowac graczy ktorzy maja mniej/wiecej przegranego czasu od ciebie!";
    }
}
