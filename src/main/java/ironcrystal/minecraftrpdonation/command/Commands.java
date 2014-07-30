package ironcrystal.minecraftrpdonation.command;

import java.text.DecimalFormat;

import ironcrystal.minecraftrpdonation.MinecraftRPDonation;
import ironcrystal.minecraftrpdonation.file.FileManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sellhead")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				ItemStack item = player.getItemInHand();
				if (item.getType() == Material.SKULL_ITEM) {
					FileConfiguration fileConfig = new YamlConfiguration();
					FileManager.loadFile(FileManager.Config, fileConfig);
					double price = fileConfig.getDouble("Head Price");
					MinecraftRPDonation.econ.bankDeposit(player.getName(), price);
					/**
					 * Remove item
					 */
					item.setAmount(item.getAmount() - 1);
					player.setItemInHand(item);
					DecimalFormat df = new DecimalFormat("0.00");
					player.sendMessage(ChatColor.BLUE + "[MinecraftRP] You got $" + df.format(price) + " for selling the head!");
				}else{
					player.sendMessage(ChatColor.RED + "[MinecraftRP] You must have a head in your hand!");
				}
			}
		}
		return false;
	}

}
