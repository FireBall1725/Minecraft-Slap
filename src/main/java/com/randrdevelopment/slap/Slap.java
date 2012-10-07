package com.randrdevelopment.slap;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.randrdevelopment.slap.command.CommandManager;
import com.randrdevelopment.slap.command.commands.SlapCommand;

public class Slap extends JavaPlugin implements Listener {
	Logger Log = Logger.getLogger("Minecraft");
	private CommandManager commandManager;
	private static Slap instance;
	
	public void onDisable() {
        // TODO: Place any custom disable code here.
    	
    	// Nothing here to disable
    }

    public void onEnable() {
    	instance = this;
        Log.info("[Slap] Starting Slap Plugin");
        
        registerCommands();
        
        Log.info("[Slap] Slap Plugin is Loaded");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commandManager.dispatch(sender, command, label, args, this);
    }

	public static Slap getInstance() {
		return instance;
	}
    
	public String getTag() {
		String tag = ChatColor.AQUA + "[Slap] " + ChatColor.WHITE;
		return tag;
	}
	
	private void registerCommands() {
		commandManager = new CommandManager();
		// Load Commands
		commandManager.addCommand(new SlapCommand(this));
	}
}

