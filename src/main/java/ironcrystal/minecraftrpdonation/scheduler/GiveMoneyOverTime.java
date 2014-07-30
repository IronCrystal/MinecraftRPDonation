package ironcrystal.minecraftrpdonation.scheduler;

import ironcrystal.minecraftrpdonation.MinecraftRPDonation;
import ironcrystal.minecraftrpdonation.player.RankedPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GiveMoneyOverTime implements Runnable {

	public void run() {		
		for (Player player : Bukkit.getOnlinePlayers()) {
			RankedPlayer rp = new RankedPlayer(player.getUniqueId());
			double salary = rp.getRankedPlayer().getSalary() / 4;
			MinecraftRPDonation.econ.bankDeposit(player.getName(), salary);
			player.sendMessage(ChatColor.GREEN + "You just recieved $" + salary + " for being online for 15 minutes!");
		}
	}
}
