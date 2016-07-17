package com.roulette.task;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.roulette.Main;
import com.roulette.config.Config;
import com.roulette.manager.Item;
import com.roulette.manager.Roulette;
import com.roulette.utils.ItemCustom;

public class RefreshGui extends BukkitRunnable {

	private Inventory roulette;
	private int turns = 0;
	private Player player = null;;
	private int itemPostion = 0;
	private Integer[] slots = new Integer[]{4, 13, 22, 31, 40,49};

	public RefreshGui(Inventory inventory, Player player) {
		this.roulette = inventory;
		roulette.setItem(8, new ItemCustom().type(Config.key.getType()).name(Config.nameKey).build());
		this.player = player;
	}

	public void run() {
		Random r = new Random();

		if (turns <= 10) {
			
			for(Item i : Main.getItems()){
				if(i.getPercentage() >= r.nextInt(100)){
					setItemSlots(i.getItem());
				}
			}
			
			try{
				Item i = Main.getItems().get(itemPostion);
				if (i.getPercentage() >= r.nextInt(100)) {
					setItemSlots(i.getItem());
				}
				itemPostion++;
			}catch(Exception e){
				Item i = Main.getItems().get(0);
				if (i.getPercentage() >= r.nextInt(100)) {
					setItemSlots(i.getItem());
				}
				itemPostion = 1;
			}
			
			turns++;
			for (int i = 0; i < roulette.getContents().length; i++)
				if (roulette.getContents()[i] == null || roulette.getContents()[i].getType() == Material.AIR || roulette.getContents()[i].getType() == Material.STAINED_GLASS_PANE)
					roulette.setItem(i, new ItemCustom().type(Material.STAINED_GLASS_PANE).amount(1).data((short) r.nextInt(14)).name(Config.glass).build());
		} else if (turns <= 15) {
			ItemStack item = new ItemCustom().type(Material.STAINED_GLASS_PANE).amount(1).data((short) r.nextInt(14)).name(Config.glass).build();

			for (int i = 0; i < roulette.getContents().length; i++)
				if (roulette.getContents()[i] == null || roulette.getContents()[i].getType() == Material.AIR	|| roulette.getContents()[i].getType() == Material.STAINED_GLASS_PANE)
					roulette.setItem(i, item);
			turns++;
		} else if (turns == 16) {
			ItemStack item = new ItemCustom().type(Material.STAINED_GLASS_PANE).amount(1).data((short) 5).name(Config.glass).build();

			for (int i = 0; i < roulette.getContents().length; i++)
				if (roulette.getContents()[i] == null || roulette.getContents()[i].getType() == Material.AIR || roulette.getContents()[i].getType() == Material.STAINED_GLASS_PANE)
					roulette.setItem(i, item);
			turns++;
		} else {
			roulette.setItem(8, roulette.getItem(4));

			player.getInventory().addItem(roulette.getItem(8));

			if (Roulette.playersTask.containsKey(player.getUniqueId())) {
				BukkitTask task = Roulette.playersTask.get(player.getUniqueId());
				task.cancel();
				Roulette.playersTask.remove(player.getUniqueId());
			}
		}
	}
	
	
	private void setItemSlots(ItemStack item){
		for(Integer slot : slots){
			roulette.setItem(slot, item);
		}
	}
	
}
