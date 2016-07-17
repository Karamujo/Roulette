package com.roulette.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import com.roulette.config.Config;
import com.roulette.manager.Roulette;

public class Listeners implements Listener{
	
	@EventHandler
	public void cancel(InventoryClickEvent event){
		if(event.getInventory().getName().equals(Config.inventory)){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void remove(InventoryCloseEvent event){
		if(event.getInventory().getName().equals(Config.inventory)){
			
			if(!(event.getPlayer() instanceof Player))
				return;
			Player player = (Player) event.getPlayer();
			
			if(Roulette.playersTask.containsKey(player.getUniqueId())){
				BukkitTask task = Roulette.playersTask.get(player.getUniqueId());
				task.cancel();
				Roulette.playersTask.remove(player.getUniqueId());
			}
		}
	}
	
	@EventHandler
	public void quit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		if(Roulette.playersTask.containsKey(player.getUniqueId())){
			BukkitTask task = Roulette.playersTask.get(player.getUniqueId());
			task.cancel();
			Roulette.playersTask.remove(player.getUniqueId());
		}
	}
	
	@EventHandler
	public void quit(PlayerKickEvent event){
		Player player = event.getPlayer();
		if(Roulette.playersTask.containsKey(player.getUniqueId())){
			BukkitTask task = Roulette.playersTask.get(player.getUniqueId());
			task.cancel();
			Roulette.playersTask.remove(player.getUniqueId());
		}
	}
	
	
	@EventHandler
	public void interat(PlayerInteractEvent event){
		if(Config.interact == true){
			Player player = event.getPlayer();
			ItemStack item = player.getItemInHand();
			if(item.getType() == Config.key.getType()){
				Roulette.openRoulleter(player);
			}
		}
	}
	
	
}