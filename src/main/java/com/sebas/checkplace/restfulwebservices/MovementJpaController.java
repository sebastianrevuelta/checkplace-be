package com.sebas.checkplace.restfulwebservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sebas.services.chess.engine.Match;
import com.sebas.services.chess.engine.Movement;

@RestController
public class MovementJpaController {

	@PutMapping(path = "/jpa/move")
	public ResponseEntity<Match> move(@RequestBody Match match) {
		System.err.println(match.getHistory());
		Movement m = new Movement();
		m = m.makeMovement(match.getBoard(),match.getTurn(),1);
		match.updateBoard(m);
		if (match.getTurn().equals("white")) {
			match.setTurn("black");
		}
		match.setHistory(match.getHistory() + m.getDescription() ); 
		return new ResponseEntity<Match>(match, HttpStatus.OK);
	}

}
