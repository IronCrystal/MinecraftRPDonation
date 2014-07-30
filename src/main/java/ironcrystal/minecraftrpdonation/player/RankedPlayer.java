package ironcrystal.minecraftrpdonation.player;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RankedPlayer {
	private UUID uuid;
	private String permission;
	protected double salary;

	public RankedPlayer(UUID uuid) {
		this.uuid = uuid;
		Player player;
		try {
			player = Bukkit.getPlayer(uuid);
			if (player.hasPermission("rank.vip")) {
				this.permission = "rank.vip";
			}
			else if (player.hasPermission("rank.sponsor")) {
				this.permission = "rank.sponsor";
			}
			else if (player.hasPermission("rank.supporter")) {
				this.permission = "rank.supporter";
			}
			else if (player.hasPermission("rank.donator")) {
				this.permission = "rank.donator";
			}else{
				this.permission = "none";
			}
		}catch(Exception ex) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED 
					+ "[MinecraftRPDonation] WARNING: Something tried creating an instance of a ranked player that is not online!");
		}
		this.salary = 0;
	}

	public UUID getUuid() {
		return uuid;
	}
	
	public String getPermission() {
		return permission;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public RankedPlayer getRankedPlayer() {
		switch(permission) {
		case "rank.vip": return new VIP(uuid);
		case "rank.sponsor": return new Sponsor(uuid);
		case "rank.supporter": return new Supporter(uuid);
		case "rank.donator": return new Donator(uuid);
		default: return new NoRank(uuid);
		}
	}
}
