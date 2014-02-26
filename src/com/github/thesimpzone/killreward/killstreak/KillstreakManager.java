package com.github.thesimpzone.killreward.killstreak;

import com.github.thesimpzone.killreward.KillReward;

public class KillstreakManager {
	
	KillReward plugin;
	private String name;
	private String playerName;
	
	public KillstreakManager(KillReward instance){
		this.plugin = instance;
	}
	
	private KillstreakDB getData(String player){
		name = player;
		playerName = player;
		KillstreakDB kdb = plugin.getDatabase().find(KillstreakDB.class).where().ieq("name",name).ieq("playerName",playerName).findUnique();
		if(kdb == null){
			kdb = new KillstreakDB();
			kdb.setPlayerName(playerName);
			kdb.setName(name);
		}
		return kdb;
	}

	
	public int getKillstreak(String player){
		return this.getData(player).getKillstreak();
	}
	public void setKillstreak(String player, int killstreak){
		this.getData(player).setKillstreak(killstreak);
	}
	
}
