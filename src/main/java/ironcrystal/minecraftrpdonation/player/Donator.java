package ironcrystal.minecraftrpdonation.player;

import ironcrystal.minecraftrpdonation.file.FileManager;

import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Donator extends RankedPlayer {

	public Donator(UUID uuid) {
		super(uuid);
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(FileManager.Config, fileConfig);
		 salary = fileConfig.getDouble("Salary.Donator");
	}
	
	@Override
	public double getSalary() {
		return salary;
	}
}
