package br.com.codadocode.codadocore.custommobs.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class MobLifeEvent implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event)   {
        if (!(event.getEntity() instanceof LivingEntity)) return;

        LivingEntity entity = (LivingEntity) event.getEntity();
        double nextLife = (entity.getHealth() - event.getDamage() > 0 ? entity.getHealth() - event.getDamage() : 0);
        String nextLifeString = String.format("%.1f", nextLife);
        String lifeLabel = ChatColor.RED + "♥" + nextLifeString + "/" + entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

        entity.setCustomName(lifeLabel);
        entity.setCustomNameVisible(true);

        new BukkitRunnable()   {

            @Override
            public void run() {
                entity.setCustomName("");
                entity.setCustomNameVisible(false);
            }
        }.runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("CodadoCore"), 20L * 5L);
    }
}
