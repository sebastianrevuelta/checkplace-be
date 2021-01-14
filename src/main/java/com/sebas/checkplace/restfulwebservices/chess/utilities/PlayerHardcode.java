package com.sebas.checkplace.restfulwebservices.chess.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerHardcode {
	
	public static List<Player> playerList = new ArrayList<Player>();
	public static long counter = 0;
	
	static {
		playerList.add(new Player(++counter,"Kasparov",2800));
		playerList.add(new Player(++counter,"Sebas",2810));
		playerList.add(new Player(++counter,"Carlsen",2820));
	}
	
	public Player deletePlayer(long id) {
		Player player = findPlayer(id);
		if (player != null) {
			playerList.remove(player);
			return player;
		}
		return null;
	}
	
	public Player findPlayer(long idToRemove) {
		Iterator<Player> i = playerList.iterator();
		while (i.hasNext()) {
			Player player = i.next();
			Long id = player.getId();
			if (id.longValue() == idToRemove) {
				return player;
			}
		}
		return null;
	}
	
	public Player save(Player player) {
		if (player.getId() == -1 || player.getId() == 0) {
			player.setId(++counter);
			playerList.add(player);
		}
		else {
			deletePlayer(player.getId());
			playerList.add(player);
		}
	    return player;
	}
	
}
