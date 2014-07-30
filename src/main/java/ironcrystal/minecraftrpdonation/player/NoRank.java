package ironcrystal.minecraftrpdonation.player;

import ironcrystal.minecraftrpdonation.file.FileManager;

import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class NoRank extends RankedPlayer {

	public NoRank(UUID uuid) {
		super(uuid);
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(FileManager.Config, fileConfig);
		 salary = fileConfig.getDouble("Salary.No Rank");
	}
	
	@Override
	public double getSalary() {
		return salary;
	}
}
