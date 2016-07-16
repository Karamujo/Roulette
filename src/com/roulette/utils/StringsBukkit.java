package com.roulette.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class StringsBukkit {
	
	public static String format(String text){
		return  text;
	}
	
	public static List<String> format(List<String> text){
		text = new ArrayList<>();
		
		for(String s : text)
			s.replaceAll("&", "§");
		return text;
	}
	
	public static void send(Player player, String text){
		player.sendMessage(format(text));
	}

	public static void send(Player player, String text, ChatColor colorAll){
		if(text.contains("&")) text.replaceAll("&", "");
		if(text.contains("§")) text.replaceAll("§", "");
		
		player.sendMessage(colorAll + text);
	}
	
}
