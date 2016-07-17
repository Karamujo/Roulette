package com.roulette.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.roulette.config.Config;
import com.roulette.manager.Roulette;

public class Commands implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))
			return false;
		
		Player player = (Player) sender;
		
		
		if(label.equalsIgnoreCase("roulette")){
		
			if(!player.hasPermission("roulette.use")){
				
				return false;
			}
			
			if(inventoryIsFull(player)){
				player.sendMessage(Config.inventoryIsFull);
				return false;
			}
			ItemStack item =  key(player.getInventory().getContents());
			if(item == null){
				player.sendMessage(Config.notContainsKey);
				return false;
			}
			int slot = keySlot(player.getInventory().getContents());
			
			Roulette.openRoulleter(player);
			
			
			player.getInventory().remove(item);
			
			if(item.getAmount() >= 2){
				item.setAmount((item.getAmount() -1));
				player.getInventory().setItem(slot,item);
			}
			
			
		}
		return false;
	}

	private ItemStack key(ItemStack[] items){
		for(ItemStack item : items)
			if(item != null && item.getType() == Config.key.getType())
				return item;
		return null;
	}
	
	private int keySlot(ItemStack[] items){
		for(int i = 0; i < items.length; i++)
			if(items[i] != null && items[i].getType() ==Config.key.getType())
				return i;
		return 0;
	}
	
	private boolean inventoryIsFull(Player player){
		   for(ItemStack item : player.getInventory().getContents())
		      if(item == null || item.getType() == Material.AIR)
		         return false;
		   return true;
	}
}
