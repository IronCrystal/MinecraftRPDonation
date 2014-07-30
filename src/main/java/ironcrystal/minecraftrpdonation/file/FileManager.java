package ironcrystal.minecraftrpdonation.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager {
	
	public static File Config;
	
	public static void initializeFiles() {
		Config = new File("plugins/MinecraftRPDonation/config.yml");
		if (!Config.exists()) {
			FileConfiguration fileConfig = new YamlConfiguration();
			fileConfig.set("Starting money", 5);
			fileConfig.set("Salary.No Rank", 1);
			fileConfig.set("Salary.Donator", 2);
			fileConfig.set("Salary.Supporter", 3);
			fileConfig.set("Salary.Sponsor", 4);
			fileConfig.set("Salary.VIP", 5);
			fileConfig.set("Head Drop Chance.No Rank", 0.2);
			fileConfig.set("Head Drop Chance.Donator", 0.35);
			fileConfig.set("Head Drop Chance.Supporter", 0.45);
			fileConfig.set("Head Drop Chance.Sponsor", 0.6);
			fileConfig.set("Head Drop Chance.VIP", 0.8);
			fileConfig.set("Head Price", 0.1);
			saveFile(Config, fileConfig);
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "[MinecraftRPDonation] Files Initialized");
	}
	
	public static void loadFile(File file, FileConfiguration fileConfig) {
		try {
			fileConfig.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveFile(File file, FileConfiguration fileConfig) {
		try {
			fileConfig.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
