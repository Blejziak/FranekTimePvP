package dev.franek.timepvp;

import dev.franek.timepvp.listener.DamageListener;
import dev.franek.timepvp.settings.TimeSettings;
import dev.franek.timepvp.util.TextUtil;
import java.nio.file.Path;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class FranekTimePvP extends JavaPlugin {

    public static final String AUTHOR = new String(new char[]{'f', 'r', 'a', 'n', 'e', 'k', 'k'});
    public static final String GITHUB = "https://github.com/Blejziak";

    private TimeSettings settings;
    private Path configFile;

    @Override
    public void onEnable() {
        this.configFile = getDataFolder().toPath().resolve("config.json");
        this.settings = TimeSettings.load(this.configFile);
        TimeSettings.save(this.settings, this.configFile);
        checkAuthor();
        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
    }

    public void checkAuthor() {
        String descAuthor = getDescription().getAuthors().isEmpty() ? "" : getDescription().getAuthors().get(0);
        if (!AUTHOR.equalsIgnoreCase(descAuthor)) {
            Bukkit.getConsoleSender().sendMessage(TextUtil.color("&8[&dFranekTimePvP&8] &7Plugin made by &d" + AUTHOR + " &8(&f" + GITHUB + "&8)"));
        } else {
            getLogger().info("Plugin made by " + AUTHOR + " (" + GITHUB + ")");
        }
    }

    public TimeSettings getSettings() {
        return this.settings;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("franek.timepvp.admin") && !sender.isOp()) {
            sender.sendMessage(TextUtil.color("&cBrak uprawnien &8(&7franek.timepvp.admin&8)"));
            return true;
        }
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            this.settings = TimeSettings.load(this.configFile);
            TimeSettings.save(this.settings, this.configFile);
            sender.sendMessage(TextUtil.color("&aPrzeladowano config &8(&7FranekTimePvP - made by " + AUTHOR + "&8)"));
            return true;
        }
        sender.sendMessage(TextUtil.color("&7FranekTimePvP v1.0 &8- &fmade by &d" + AUTHOR + " &8(&7/timepvp reload&8)"));
        return true;
    }
}
