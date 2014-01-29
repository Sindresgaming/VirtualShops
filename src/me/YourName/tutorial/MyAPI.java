package me.YourName.tutorial;

import org.bukkit.entity.Player;

public class MyAPI {

	public static void giveSilver(Player p, int i) {
		Tutorial.config.set(p.getName() + ".Silver",
				Tutorial.config.getInt(p.getName() + ".Silver", 0) + i);
		Tutorial.saveFile();
		p.sendMessage("§2§l$" + i + " silver received!");
	}

	public static void takeSilver(Player p, int i) {
		Tutorial.config.set(p.getName() + ".Silver",
				Tutorial.config.getInt(p.getName() + ".Silver", 0) - i);
		Tutorial.saveFile();
		p.sendMessage("§c§l$" + i + " silver taken!");
	}

	public static boolean hasEnough(Player p, int i) {
		if (Tutorial.config.getInt(p.getName() + ".Silver") >= i)
			return true;
		return false;
	}
}