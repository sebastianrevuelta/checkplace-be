package com.sebas.checkplace.restfulwebservices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sebas.services.chess.engine.Match;
import com.sebas.services.chess.engine.Movement;
import com.sebas.services.chess.engine.Square;
import com.sebas.services.chess.engine.UtilChess;

@RestController
public class MovementJpaController {

	String EOL = System.getProperty("line.separator");
	
	@PutMapping(path = "/jpa/move")
	public ResponseEntity<Match> move(@RequestBody Match match) {
		
		match.getBoard().setSquares(UtilChess.addPieceToSquares(match.getBoard().getSquares()));
		Movement m = new Movement();
		m = m.makeMovement(match.getBoard(),match.getTurn(),1);
		match.updateBoard(m);
		boolean checkmate = match.getBoard().checkMate(m);
		if (match.getTurn().equals("white")) {
			match.setTurn("black");
			match.setHistory(match.getHistory() + match.getMovement() + ". " + m.getDescription() ); 
		}
		else {
			match.setTurn("white");
			match.setHistory(match.getHistory() + "..." + m.getDescription() + EOL); 
			match.setMovement(1+match.getMovement());
		}
		if (checkmate) {
			match.setHistory(match.getHistory() + "++. END OF THE GAME!!!");
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square square = match.getBoard().getSquares()[i][j];
				square.setPiece(null);
			}}
		return new ResponseEntity<Match>(match, HttpStatus.OK);
	}

}
