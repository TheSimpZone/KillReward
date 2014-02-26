package com.github.thesimpzone.killreward.killstreak;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

import com.github.thesimpzone.killreward.KillReward;
import com.github.thesimpzone.killreward.RefreshTask;
import com.github.thesimpzone.killreward.pvpstats.Database;
import com.github.thesimpzone.killreward.pvpstats.PlayerStats;

public class KillstreakListener implements Listener{

	private KillReward plugin;
	boolean isEntityNull = true;
	boolean isNameNull = true;

	public KillstreakListener(KillReward killreward){
		plugin = killreward;
		plugin.getServer().getPluginManager().registerEvents(this,killreward);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onKill(PlayerDeathEvent e)
	{
		isEntityNull = e.getEntity().getKiller() == null || e.getEntity() == null;
		if(!isEntityNull){
			isNameNull = e.getEntity().getKiller().getName() == null || e.getEntity().getName() == null;
			if(e.getEntity().getKiller() instanceof Player && !isNameNull){
				Player killer = e.getEntity().getKiller();
				Player killed = e.getEntity();
				final PlayerStats killedcache = Database.getCacheIfAbsent(killed);
				System.out.println(killedcache);
				if (killedcache != null) {
					killedcache.incrementKills();
					final PlayerStats killercache = Database.getCacheIfAbsent(killer);
					System.out.println(killercache);
					if (killercache != null) {
						killercache.incrementKills();
						KillReward.economy.depositPlayer(e.getEntity().getKiller().getName(), killercache.getLaststreak()+9);
					}
				}
			}
		}
	}
	
    @EventHandler
    public void onJoin(PlayerJoinEvent join) {
    	final Player player = join.getPlayer();
    	Database.loadAccount(player.getName());
    	KillReward krinstance = KillReward.getInstance();
    	System.out.println("ktgrss: " + krinstance);
    	if(krinstance != null){
    		System.out.println("krinstance not nukl");
    		RefreshTask rftask = krinstance.getRefreshTask();
    		if(rftask != null){
        		System.out.println("rftask not nukl");
    			rftask.addToQueue(player);
    		}
    	}
    }
        
}