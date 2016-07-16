package com.roulette.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemCustom {
	
	private ItemStack item;
	
	public ItemCustom() {
		item = new ItemStack(Material.AIR, 1);
	}
	
	public ItemCustom type(Material type){
		item.setType(type);
		return this;
	}
	
	public ItemCustom data(short value){
		item = new ItemStack(item.getType(), item.getAmount(),  value);
		return this;
	}
	
	public ItemCustom amount(int quantia){
		item.setAmount(quantia);
		return this;
	}
	
	public ItemCustom amount(String quantia){
		item.setAmount(Integer.valueOf(quantia));
		return this;
	}
	
	public ItemCustom name(String name){
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(StringsBukkit.format(name));
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemCustom lore(List<String> lore){
		ItemMeta meta = item.getItemMeta();
		meta.setLore((StringsBukkit.format(lore)));
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemCustom ownerSkull(String owner, String name){
		item = new ItemStack(Material.SKULL_ITEM,  1, (short) 3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(name);
		meta.setDisplayName(StringsBukkit.format(name));
		item.setItemMeta(meta);
		return this;
	}
	

	public ItemStack build(){
		return item;
	}
}
