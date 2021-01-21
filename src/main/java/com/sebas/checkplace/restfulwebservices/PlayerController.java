package com.sebas.checkplace.restfulwebservices;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sebas.checkplace.restfulwebservices.chess.utilities.Player;
import com.sebas.checkplace.restfulwebservices.chess.utilities.PlayerHardcode;

@RestController
public class PlayerController {
	
	@GetMapping(path = "/players")
	public List<Player> getPlayers() {
		return PlayerHardcode.playerList;
	}
	
	@GetMapping(path = "/players/{id}")
	public Player getPlayer(@PathVariable int id) {
		PlayerHardcode pc = new PlayerHardcode();
		Player player = pc.findPlayer(id);
		if (player != null) {
			return player;
		}
		return null;
	}
	
	@DeleteMapping(path = "/deletePlayer/{id}")
	public ResponseEntity<Void> deletePlayer(@PathVariable int id) {
		PlayerHardcode pc = new PlayerHardcode();
		Player player = pc.deletePlayer(id);
		if (player != null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping(path = "/updatePlayer/{id}")
	public ResponseEntity<Player> updatePlayer(@PathVariable int id, @RequestBody Player player) {
		PlayerHardcode pc = new PlayerHardcode();
		Player playerUpdated = pc.save(player);
		return new ResponseEntity<Player>(playerUpdated, HttpStatus.OK);
	}
	
	@PostMapping(path = "/addPlayer")
	public ResponseEntity<Void> addPlayer(@RequestBody Player player) {
		PlayerHardcode pc = new PlayerHardcode();
		Player playerCreated = pc.save(player);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(playerCreated.getId()).toUri();
		
		
		return ResponseEntity.created(uri).build();
	}
}
