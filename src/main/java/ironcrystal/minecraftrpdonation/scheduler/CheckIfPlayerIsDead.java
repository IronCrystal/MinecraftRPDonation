package ironcrystal.minecraftrpdonation.scheduler;

import ironcrystal.minecraftrpdonation.file.FileManager;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class CheckIfPlayerIsDead implements Runnable {
	private Player victim;
	private Player damager;

	public CheckIfPlayerIsDead(Player victim, Player damager) {
		this.victim = victim;
		this.damager = damager;
	}

	@Override
	public void run() {
		if (victim.isDead()) {
			Random rand = new Random();
			double chance = rand.nextDouble();
			Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "[MinecraftRPDonation] Chance of dropping head: " + getDropChance(damager));
			Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "[MinecraftRPDonation] Random number: " + chance);
			if (chance < getDropChance(damager)) {
				ItemStack skull = Skull(victim.getName(), victim.getName(), 1);
				victim.getWorld().dropItemNaturally(victim.getLocation(), skull);
			}
		}
	}

	private ItemStack Skull(String skullOwner, String displayName, int quantity) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, quantity, (short) SkullType.PLAYER.ordinal());
		SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		skullMeta.setOwner(skullOwner);
		if (displayName != null) {
			skullMeta.setDisplayName(ChatColor.RESET + displayName);
		}
		skull.setItemMeta(skullMeta);
		return skull;
	}

	private double getDropChance(Player damager) {
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(FileManager.Config, fileConfig);
		
		if (damager.hasPermission("staff.owner")) {
			return 1.00;
		}
		else if (damager.hasPermission("rank.vip")) {
			return fileConfig.getDouble("Head Drop Chance.VIP");
		}
		else if (damager.hasPermission("rank.sponsor")) {
			return fileConfig.getDouble("Head Drop Chance.Sponsor");
		}
		else if (damager.hasPermission("rank.supporter")) {
			return fileConfig.getDouble("Head Drop Chance.Supporter");
		}
		else if (damager.hasPermission("rank.donator")) {
			return fileConfig.getDouble("Head Drop Chance.Donator");
		}
		return fileConfig.getDouble("Head Drop Chance.No Rank");
	}
}
