package com.roulette;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.roulette.commands.Commands;
import com.roulette.config.Config;
import com.roulette.listeners.Listeners;
import com.roulette.manager.Item;

/**
 * 
 * @author Karamujo
 * @version 1.0.4
 */

@SuppressWarnings("all")
public class Main extends JavaPlugin {
	
	private static Main plugin;
	private static ArrayList<Item> items = new ArrayList<Item>();
	
	
	public static Main getPlugin(){
		return plugin;
	}
	
	
	public void onEnable() {
		plugin = this;
		
		saveDefaultConfig();
		Config.loadConfig();
		loadItems();
		
		getCommand("roulette").setExecutor(new Commands());
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		log("[Roulette] Plugin iniciado ");
		log("[Roulette] " + getPlugin().getDescription().getVersion());
	}
	
	
	public void onDisable() {
		HandlerList.unregisterAll();
	}
	
	public static void log(String message){
		Bukkit.getLogger().info(message);
	}
	
	private void loadItems(){
		for(String s : Config.items){
			String[] args = s.split(",");
			
			String item = args[0];
			String amount = args[1];
			String percentage = args[2];
			
			ItemStack itemS;
			
			if(item.contains(":"))
				itemS= new ItemStack(Material.getMaterial(Integer.valueOf(item.split(":")[0])), Integer.valueOf(amount), Short.valueOf(item.split(":")[1]));
			else
				itemS = new ItemStack(Material.getMaterial(Integer.valueOf(item)), Integer.valueOf(amount));
		
			items.add(new Item(itemS, Integer.valueOf(percentage)));
		}
	}
	
	public static ArrayList<Item> getItems(){
		return items;
	}
}