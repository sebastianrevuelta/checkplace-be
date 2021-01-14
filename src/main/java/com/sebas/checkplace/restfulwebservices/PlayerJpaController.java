package com.sebas.checkplace.restfulwebservices;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sebas.checkplace.restfulwebservices.chess.utilities.Player;
import com.sebas.checkplace.restfulwebservices.chess.utilities.PlayerJpaRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class PlayerJpaController {
	
	@Autowired
	private PlayerJpaRepository playerJpaRepo;
	
	@GetMapping(path = "/jpa/players")
	public List<Player> getPlayers() {
		return playerJpaRepo.findAll();
	}
	
	@GetMapping(path = "/jpa/players/{id}")
	public Player getPlayer(@PathVariable Long id) {
		return playerJpaRepo.findById(id).get();
	}
	
	@DeleteMapping(path = "/jpa/deletePlayer/{id}")
	public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
		playerJpaRepo.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path = "/jpa/updatePlayer/{id}")
	public ResponseEntity<Player> updatePlayer(@PathVariable int id, @RequestBody Player player) {
		Player playerUpdated = playerJpaRepo.save(player);
		return new ResponseEntity<Player>(playerUpdated, HttpStatus.OK);
	}
	
	@PostMapping(path = "/jpa/addPlayer")
	public ResponseEntity<Void> addPlayer(@RequestBody Player player) {
		Player playerCreated = playerJpaRepo.save(player);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(playerCreated.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
}
