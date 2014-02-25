package com.github.thesimpzone.killreward;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;

import net.milkbowl.vault.economy.Economy;

import com.github.games647.scoreboardstats.pvpstats.Database;
import com.github.games647.scoreboardstats.pvpstats.PlayerStats;

public class KillstreakListener implements Listener{

	Economy economy;
	private KillReward plugin;

	public KillstreakListener(KillReward killreward){
		plugin = killreward;
		plugin.getServer().getPluginManager().registerEvents(this,killreward);
		plugin.getLogger().info("KsL constructed");
	}


	@EventHandler(priority = EventPriority.MONITOR)
	public void onKill(PlayerDeathEvent e)
	{
		plugin.getLogger().info("KsL PDE logged");
		if(e.getEntity().getKiller() instanceof Player){
			Player killer = e.getEntity().getKiller();
			Player killed = e.getEntity();
			PlayerStats stats = Database.getDatabaseInstance().find(PlayerStats.class).where().eq("playername", killer.getName()).findUnique();
			plugin.getLogger().info("KsL PDE killedByPlayer logged. Killed: " + killed.getName() + " Killer: " + killer.getName());

			stats.setPlayername("0070071");
			economy.depositPlayer("0070071", stats.getKillstreak()+10);
			killer.sendMessage("You killed " + killed.getName() + "and should receive $" + stats.getKillstreak()+10);
		}
	}

}
