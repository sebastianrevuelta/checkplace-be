package com.sebas.checkplace.restfulwebservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sebas.services.chess.engine.Board;
import com.sebas.services.chess.engine.Movement;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class MovementController {

	@PutMapping(path = "/move")
	public ResponseEntity<Board> move(@RequestBody Board board) {
		Movement m = new Movement();
		m = m.makeMovement(board,"white",1);
		board.update(m,"white");
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	@GetMapping(path = "/move/{history}")
	public Movement move(@PathVariable String history) {
		Board board = new Board(); //TODO: Create board from history
		Movement m = new Movement();
		String turn = "white"; //TODO: Turn should be given by the board after building the board from history
		return m.makeMovement(board,turn,1); //TODO: Deep Level should be configured from interface
	}
	


}
