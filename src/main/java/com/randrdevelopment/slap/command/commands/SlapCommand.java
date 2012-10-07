package com.randrdevelopment.slap.command.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.randrdevelopment.slap.Slap;
import com.randrdevelopment.slap.command.BaseCommand;

public class SlapCommand extends BaseCommand{

	public SlapCommand(Slap plugin) {
		super(plugin);
		name = "Slap";
		description = "Slaps another player.";
		usage = "/slap <player> [message]";
		minArgs = 1;
		maxArgs = 2;
		identifiers.add("slap");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args){
		String user = args[0];
		String message = "was slapped by a giant trout."; // todo make load from config
		
		if (args.length == 2)
		{
			message = args[1];
		}
		
		// Validate the user has permission...
		if (!sender.hasPermission("slap.allowtoslap"))
		{
			sender.sendMessage(plugin.getTag() + ChatColor.RED + "You do not have permission to use this command.");
			return;
		}
			
		String playerDisplayName = "";
		
		// Validate the user is online...
		Player targetPlayer = Bukkit.getServer().getPlayer(user);
		if (targetPlayer != null){
			playerDisplayName = targetPlayer.getDisplayName();
		} else {
			sender.sendMessage(plugin.getTag() + ChatColor.RED + "Player " + user + " is not online");
			return;
		}
				
		// Broadcast message out...
		sender.getServer().broadcastMessage(plugin.getTag() + playerDisplayName + " " + message);
		
		// Deal damage to the user...
		if (!sender.hasPermission("slap.bypassdamage"))
		{
			// User can get damaged by the slap command
			int health = targetPlayer.getHealth();
			int newHealth = health - 4;
			if (newHealth < 0) {
				newHealth = 0; // lets kill the player
			}
			targetPlayer.setHealth(newHealth); // to-do make damage adjustable
		}
	}
	
}
