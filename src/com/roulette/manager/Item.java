package com.roulette.manager;

import org.bukkit.inventory.ItemStack;

public class Item {

	private ItemStack item;
	private int percentage;
	
	public Item(ItemStack item,int pecentage) {
		this.item = item;
		this.percentage = pecentage;
	}

	public ItemStack getItem() {
		return item;
	}

	public int getPercentage() {
		return percentage;
	}
	
}
