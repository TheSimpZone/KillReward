package com.github.thesimpzone.killreward;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.milkbowl.vault.economy.Economy;
import com.github.games647.scoreboardstats.pvpstats.PlayerStats;

public class KillstreakManager {

	PlayerStats pstats;
	Economy economy;
	
	@EventHandler
	public void onKill(PlayerDeathEvent e)
	{
	String killer = e.getEntity().getKiller().getName();
	
	pstats.setPlayername(killer);
	economy.depositPlayer(killer, pstats.getKillstreak()+10);
	}
	
}
