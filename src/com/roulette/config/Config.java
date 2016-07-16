package com.roulette.config;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.roulette.Main;

public class Config {

	public static String notContainsKey;
	public static String inventoryIsFull;
	public static ArrayList<String> items;
	public static String nameKey;
	public static String glass;
	public static String inventory;
	public static boolean interact;
	public static long velocitySlide;
	
	public static ItemStack key;
	
	public static String notPermission;
	
	@SuppressWarnings("deprecation")
	public static void loadConfig(){
		try{
			notContainsKey = Main.getPlugin().getConfig().getString("NotContainsKey").replace("&", "§");
			inventoryIsFull = Main.getPlugin().getConfig().getString("InventoryIsFull").replace("&", "§");
			items = (ArrayList<String>) Main.getPlugin().getConfig().getStringList("Items");
			nameKey = Main.getPlugin().getConfig().getString("ItemConfig.Key").replace("&", "§");
			glass = Main.getPlugin().getConfig().getString("ItemConfig.Glass").replace("&", "§");
			inventory = Main.getPlugin().getConfig().getString("Inventory").replace("&", "§");
			
			if(Main.getPlugin().getConfig().getString("Key").contains(":"))
				key = new ItemStack(Material.getMaterial(Integer.valueOf(Main.getPlugin().getConfig().getString("Key").split(":")[0])), 1 , Short.valueOf(Main.getPlugin().getConfig().getString("Key").split(":")[1]));
			else
				key = new ItemStack(Material.getMaterial(Integer.valueOf(Main.getPlugin().getConfig().getString("Key"))));
			
			interact = Main.getPlugin().getConfig().getBoolean("Interact");
			velocitySlide = Main.getPlugin().getConfig().getLong("VelocitySlide");
			notPermission = Main.getPlugin().getConfig().getString("NotPermission").replace("&", "§");;
			
		}catch(Exception e){
			throw new IllegalArgumentException("Invalid config: " + e.getMessage() + "\n");
		}
	}
	
	
}
