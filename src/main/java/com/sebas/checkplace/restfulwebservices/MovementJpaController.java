package com.sebas.checkplace.restfulwebservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sebas.services.chess.engine.Board;
import com.sebas.services.chess.engine.Match;
import com.sebas.services.chess.engine.Movement;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class MovementJpaController {

	@PutMapping(path = "/jpa/move")
	public ResponseEntity<Board> move(@RequestBody Match match) {
		Movement m = new Movement();
		m = m.makeMovement(match.getBoard(),match.getTurno(),1);
		match.getBoard().update(m,match.getTurno());
		return new ResponseEntity<Board>(match.getBoard(), HttpStatus.OK);
	}

}
