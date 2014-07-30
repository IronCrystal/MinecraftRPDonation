package ironcrystal.minecraftrpdonation.player;

import ironcrystal.minecraftrpdonation.file.FileManager;

import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Supporter extends RankedPlayer {

	public Supporter(UUID uuid) {
		super(uuid);
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(FileManager.Config, fileConfig);
		 salary = fileConfig.getDouble("Salary.Supporter");
	}
	
	@Override
	public double getSalary() {
		return salary;
	}
}
