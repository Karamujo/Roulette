package com.roulette.manager;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitTask;

import com.roulette.Main;
import com.roulette.config.Config;
import com.roulette.task.RefreshGui;

@SuppressWarnings("all")
public class Roulette {
	
	public static HashMap<UUID, BukkitTask> playersTask = new HashMap<>();
	
	
	public static void openRoulleter(Player player){
		Inventory inventory = Bukkit.createInventory(null, 54, Config.inventory);
		
		BukkitTask tas = Bukkit.getScheduler().runTaskTimer(Main.getPlugin(), new RefreshGui(inventory, player), 0L, Config.velocitySlide);
		
		playersTask.put(player.getUniqueId(), tas);
		
		player.openInventory(inventory);
	}
	
	
}
