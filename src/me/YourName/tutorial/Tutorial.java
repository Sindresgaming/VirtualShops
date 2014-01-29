package me.YourName.tutorial;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Tutorial extends JavaPlugin implements Listener {
	
	public static FileConfiguration config;

	public static Tutorial plugin = null;

	public void onEnable() {
		getServer().getPluginManager().registerEvents(new EventHandlers(), this);
		
		config = getConfig();
		
		plugin = this;
	}
	
	public static void saveFile(){
		plugin.saveConfig();
	}

	public ItemStack createItem(Material material, int amount, short shrt,
			String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);

		item.setItemMeta(meta);
		return item;
	}

	static Inventory shop;
	{
		shop = Bukkit.createInventory(null, 9, "§0§nMy Custom Shop");

		shop.setItem(
				0,
				createItem(Material.APPLE, 1, (short) 0, "§3Apple",
						"§fPrice §6200 §fSilver"));
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] a) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("shop")) {
			player.openInventory(shop);
		}
		return false;
	}
}