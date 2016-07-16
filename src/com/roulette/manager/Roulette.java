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
	
	
	public static void openRoulleter(Player p){
		Inventory inv = Bukkit.createInventory(null, 54, Config.inventory);
		
		BukkitTask tas = Bukkit.getScheduler().runTaskTimer(Main.getPlugin(), new RefreshGui(inv,p), 0L, 10L);
		
		playersTask.put(p.getUniqueId(), tas);
		
		p.openInventory(inv);
	}
	
	
}
