package com.github.thesimpzone.killreward.killstreak;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;

import com.github.thesimpzone.killreward.KillReward;

public class KillstreakListener implements Listener{

	private KillReward plugin;
	private KillstreakManager ksm;
	private int killstreak;

	public KillstreakListener(KillReward killreward){
		plugin = killreward;
		plugin.getServer().getPluginManager().registerEvents(this,killreward);
		plugin.getLogger().info("KsL constructed");
	}


	@EventHandler(priority = EventPriority.MONITOR)
	public void onKill(PlayerDeathEvent e)
	{
		boolean isEntityNull = e.getEntity().getKiller() == null || e.getEntity() == null;
		boolean isNameNull = e.getEntity().getKiller().getName() == null || e.getEntity().getName() == null;
		plugin.getLogger().info("KsL PDE logged");
		if(e.getEntity().getKiller() instanceof Player && !isEntityNull && !isNameNull){
			
			Player killer = e.getEntity().getKiller();
			Player killed = e.getEntity();
			
			//killstreak = 1;
			
			killstreak = ksm.getKillstreak(killer.getName());
			killstreak++;
			ksm.setKillstreak(killer.getName(), killstreak);
			ksm.setKillstreak(killed.getName(),0);
			plugin.getLogger().info("KsL PDE killedByPlayer logged. Killed: " + killed.getName() + " Killer: " + killer.getName() + " Killstreak: " + killstreak);
			KillReward.economy.depositPlayer(killer.getName(), 9+killstreak);
			killer.sendMessage("You killed " + killed.getName() + " and should receive $" + 9+killstreak);
		}
	}
}
