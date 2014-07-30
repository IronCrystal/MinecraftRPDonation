package ironcrystal.minecraftrpdonation;

import ironcrystal.minecraftrpdonation.command.Commands;
import ironcrystal.minecraftrpdonation.event.Listeners;
import ironcrystal.minecraftrpdonation.file.FileManager;
import ironcrystal.minecraftrpdonation.scheduler.GiveMoneyOverTime;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftRPDonation extends JavaPlugin {
	
	public static Economy econ = null;
	
	@Override
	public void onEnable() {
		FileManager.initializeFiles();
		Listeners.registerEvents(this);
		if (!setUpDependencies()) {
			getServer().getPluginManager().disablePlugin(this);
		}
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new GiveMoneyOverTime(), 0L, 18000L);
		Commands commands = new Commands();
		getCommand("sellhead").setExecutor(commands);
	}

	@Override
	public void onDisable() {
		
	}
	
	private boolean setUpDependencies() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[MinecraftRPDonation] Disabled due to no Vault found!");
            return false;
        }
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "[MinecraftRPDonation] Hooked into Vault!");
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
        	Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[MinecraftRPDonation] Disabled due to no Economy plugin found!");
            return false;
        }
        econ = rsp.getProvider();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "[MinecraftRPDonation] Hooked into Economy Plugin!");
        return true;
	}
}
