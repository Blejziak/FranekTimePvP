package dev.franek.timepvp.listener;

import dev.franek.timepvp.FranekTimePvP;
import dev.franek.timepvp.util.TextUtil;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public final class DamageListener implements Listener {

    private final FranekTimePvP plugin;

    public DamageListener(FranekTimePvP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!(event.getDamager() instanceof Player) || !(event.getEntity() instanceof Player)) {
            return;
        }
        Player attacker = (Player) event.getDamager();
        Player victim = (Player) event.getEntity();

        int attackerTime = attacker.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
        int victimTime = victim.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
        int limit = this.plugin.getSettings().options.pvpTimeSeconds;

        if ((attackerTime < limit && victimTime >= limit) || (attackerTime >= limit && victimTime < limit)) {
            event.setCancelled(true);
            attacker.sendMessage(TextUtil.color(this.plugin.getSettings().options.cantAttackMessage));
        }
    }
}
