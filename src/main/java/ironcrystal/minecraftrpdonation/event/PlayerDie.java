package ironcrystal.minecraftrpdonation.event;

import ironcrystal.minecraftrpdonation.MinecraftRPDonation;
import ironcrystal.minecraftrpdonation.scheduler.CheckIfPlayerIsDead;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.projectiles.ProjectileSource;

public class PlayerDie implements Listener {
	private MinecraftRPDonation main;
	public PlayerDie(MinecraftRPDonation main) {
		this.main = main;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDie(EntityDamageByEntityEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Player) {
			Player victim = (Player) entity;
			Entity entity2 = event.getDamager();
			if (entity2 instanceof Player) {
				Player damager = (Player) entity2;
				Bukkit.getScheduler().runTaskLater(main, new CheckIfPlayerIsDead(victim, damager), 2L);						
			}
			else if (entity2 instanceof Arrow) {
				Arrow arrow = (Arrow) entity2;
				ProjectileSource source = arrow.getShooter();
				if (source instanceof Player) {
					Player shooter = (Player) source;
					if (shooter.getName().equalsIgnoreCase("IronCrystal")) {
						event.setDamage(0);
						List<Entity> entities = victim.getNearbyEntities(10, 5, 10);
						for (Entity e : entities) {
							if (e instanceof Player) {
								Player p = (Player) e;
								if (!p.getName().equalsIgnoreCase("IronCrystal")) {
									EntityDamageByEntityEvent playerEvent = new EntityDamageByEntityEvent(p, victim, DamageCause.ENTITY_ATTACK, victim.getHealth()); // Create the event here
									Bukkit.getServer().getPluginManager().callEvent(playerEvent);  // Call the event
									break;
								}
							}
						}
					}
				}
			}
		}
	}
}



/*package ironcrystal.minecraftrpdonation.event;

import java.util.ArrayList;
import java.util.List;

import ironcrystal.minecraftrpdonation.MinecraftRPDonation;
import ironcrystal.minecraftrpdonation.scheduler.CheckIfPlayerIsDead;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.projectiles.ProjectileSource;

public class PlayerDie implements Listener {
	private MinecraftRPDonation main;
	public PlayerDie(MinecraftRPDonation main) {
		this.main = main;
	}

	private List<Player> players = new ArrayList<Player>();

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDie(EntityDamageByEntityEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Player) {
			Player victim = (Player) entity;
			Entity entity2 = event.getDamager();
			if (entity2 instanceof Player) {
				Player damager = (Player) entity2;
				Bukkit.getScheduler().runTaskLater(main, new CheckIfPlayerIsDead(victim, damager), 2L);						
			}
			else if (entity2 instanceof Arrow) {
				Arrow arrow = (Arrow) entity2;
				ProjectileSource source = arrow.getShooter();
				if (source instanceof Player) {
					Player shooter = (Player) source;
					if (shooter.getName().equalsIgnoreCase("IronCrystal")) {
						players.add(victim);
						victim.setHealth(0);
					}
				}
			}
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (players.contains(event.getEntity())) {
			event.setDeathMessage(event.getEntity().getName() + " died!");
		}
	}
}
 **/