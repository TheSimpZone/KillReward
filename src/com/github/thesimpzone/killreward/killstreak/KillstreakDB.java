package com.github.thesimpzone.killreward.killstreak;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.validation.NotEmpty;
import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name="killstreaks")
public class KillstreakDB {

	@Id
	@NotNull
	private int id;
	
	@NotNull
	private String playerName;

	@NotEmpty
	private String name;

	private int killstreak;
	
	@NotEmpty
	private String Test;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKillstreak() {
		return killstreak;
	}

	public void setKillstreak(int killstreak) {
		this.killstreak = killstreak;
	}

	public String getTest() {
		return Test;
	}

	public void setTest(String test) {
		Test = test;
	}
	
}
