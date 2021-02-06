package com.sebas.checkplace.restfulwebservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sebas.services.chess.engine.Board;
import com.sebas.services.chess.engine.Movement;

@RestController
public class MovementController {

	@PutMapping(path = "/move")
	public ResponseEntity<Board> move(@RequestBody Board board) {
		Movement m = new Movement();
		m = m.makeMovement(board,"white",1);
		board.update(m,"white");
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
}
