package me.YourName.tutorial;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventHandlers implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		if (!Tutorial.config.contains(p.getName())) {
			Tutorial.config.set(p.getName() + ".Silver", 0);
		}
	}

	@EventHandler
	public void onKill(EntityDeathEvent e) {

		if (e.getEntity() instanceof Monster) {
			Monster m = (Monster) e.getEntity();
			if (m.getKiller() instanceof Player) {
				Player p = m.getKiller();

				MyAPI.giveSilver(p, 200);
			}
		}

		if (e.getEntity() instanceof Villager) {
			Villager v = (Villager) e.getEntity();
			if (v.getKiller() instanceof Player) {
				Player p = v.getKiller();
				MyAPI.takeSilver(p, 200);
			}
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();

		if (event.getInventory().getName().equals(Tutorial.shop.getName())) {
			event.setCancelled(true);

			if (event.getCurrentItem() == null) {
				return;
			}

			if (!(event.getCurrentItem().hasItemMeta())) {
				return;
			}

			if (event.getCurrentItem().getItemMeta().getDisplayName()
					.equals("§3Apple")) {
				if (MyAPI.hasEnough(p, 25)) {
					MyAPI.takeSilver(p, 25);
				} else {
					p.sendMessage("§cYou're too poor!");
				}
			}
		}
	}
}
