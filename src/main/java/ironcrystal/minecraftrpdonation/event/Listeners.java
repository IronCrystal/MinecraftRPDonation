package ironcrystal.minecraftrpdonation.event;

import ironcrystal.minecraftrpdonation.MinecraftRPDonation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Listeners {

	private static PlayerDie playerDie;

	private static void initializeListeners(MinecraftRPDonation main) {
		playerDie = new PlayerDie(main);
	}

	public static void registerEvents(MinecraftRPDonation main) {
		Listeners.initializeListeners(main);
		Bukkit.getServer().getPluginManager().registerEvents(playerDie, main);
		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "[MinecraftRPDonation] Listeners Registered");
	}
}
