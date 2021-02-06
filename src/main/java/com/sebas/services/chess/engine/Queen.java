package com.sebas.services.chess.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * This is the Queen class 
 * @author srevuelta
 *
 */
public class Queen extends Piece {
	
	public Queen() {
		 super.setType("queen");
		 super.setValue(9);
	}
	
	/***
	 * move the queen
	 * @param turn
	 * @return
	 */
	public List<Movement> move(String from, String turn, String type) {

		log.info(turn + " " + this.getType() + " is going to move from: " + from);
		List<Movement> possibleMoves = new ArrayList<Movement>();

		Piece tower = new Tower();
		Piece bishop = new Bishop();
		tower.setType("queen");
		tower.setValue(9);
		bishop.setType("queen");
		bishop.setValue(9);
		List<Movement> towerMoves = tower.move(from, turn, type);
		List<Movement> bishopMoves = bishop.move(from, turn, type);
		possibleMoves.addAll(towerMoves);
		possibleMoves.addAll(bishopMoves);
		
		return possibleMoves;
	}
	
	/**
	 * TODO: check if the move is possible
	 */
	public boolean isRealMove(Movement movement, Board board, String turn) {
		
		List<Square> squares = getSquares(board, movement);
		Iterator<Square> i = squares.iterator();
		while (i.hasNext()) {
			Square square = i.next();
			if (!square.isEmpty()) {
				Piece p = square.getPiece();
				if (p != null && turn.equals(square.getPiece().getColor())) {
					return false;
				}
			}
		}

		return true;
	}

	private List<Square> getSquares(Board board, Movement movement) {
		List<Square> squares = new ArrayList<Square>();
		Tower tower = new Tower();
		Bishop bishop = new Bishop();
		List<Square> squaresT = tower.getSquares(board, movement);
		List<Square> squaresB = bishop.getSquares(board, movement);
		squares.addAll(squaresB);
		squares.addAll(squaresT);
		return squares;
	}
}
