package com.sebas.services.chess.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is the Tower class
 * @author srevuelta
 *
 */
public class Tower extends Piece {

	Tower() {
		super.setType("tower");
		super.setValue(5);
	}


	/***
	 * move the tower
	 * @param turn
	 * @return
	 */
	public List<Movement> move(String from, String turn, String type) {
		
		log.info(turn + " " + type + " is going to move from: " + from);
		List<Movement> possibleMoves = new ArrayList<Movement>();

		String to;

		String rowdestiny = "a";
		String columndestiny = "1";

		int valueColumn = UtilChess.calculateHorizontal(from);
		int valueRow = UtilChess.calculateVertical(from);

		for (int i=1; i < 8; i++) {
			if (valueRow+i < 8) {
				int row = new Integer(valueRow+i).intValue();
				rowdestiny = UtilChess.calculateVertical(row);
				int column = new Integer(valueColumn).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move1 = new Movement(this,from,to);
				possibleMoves.add(move1);	//TODO
			}

			if (valueRow-i >=0) {
				int row = new Integer(valueRow+i).intValue();
				rowdestiny = UtilChess.calculateVertical(row);
				int column = new Integer(valueColumn).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;
				Movement move2 = new Movement(this,from,to);
				possibleMoves.add(move2);
			}

			if (valueColumn+i < 8) {
				int row = new Integer(valueRow).intValue();
				rowdestiny = UtilChess.calculateVertical(row);
				int column = new Integer(valueColumn+i).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move3 = new Movement(this,from,to);
				possibleMoves.add(move3);
			}
			if (valueColumn-i >=0) {
				
				int row = new Integer(valueRow).intValue();
				rowdestiny = UtilChess.calculateVertical(row);
				int column = new Integer(valueColumn-i).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;
				Movement move4 = new Movement(this,from,to);
				possibleMoves.add(move4);
			}
		}
		return possibleMoves;
	}

	/**
	 * check if the move is possible
	 */
	public boolean isRealMove(Movement movement, Board board, String turn) {

		List<Square> squares = getSquares(board, movement);
		Iterator<Square> i = squares.iterator();
		while (i.hasNext()) {
			Square square = i.next();
			if (!square.isEmpty()) {
				Piece p = square.getPieza();
				if (p != null) {
					if (turn.equals(square.getPieza().getColor())) {
						return false;
					}
					else { //it is different color
						if ((square.getHorizontal()+square.getVertical()).equals(movement.getDestiny())) {
							return true;
						}
						else {
							return false;
						}
					}
				}
			}
		}
		return true;
	}


	
	
	/**
	 * 
	 * @param board
	 * @param movement
	 * @return
	 */
	
	public List<Square> getSquares(Board board, Movement movement) {

		List<Square> squares = new ArrayList<Square>();

		String from = movement.getOrigin();
		String to = movement.getDestiny();

		int horizontalTo = UtilChess.calculateHorizontal(to);
		int verticalTo = UtilChess.calculateVertical(to);

		int horizontalFrom = UtilChess.calculateHorizontal(from);
		int verticalFrom = UtilChess.calculateVertical(from);

		if (horizontalTo == horizontalFrom) {
			if (verticalFrom < verticalTo) {
				for (int i = verticalFrom+1; i <= verticalTo; i++) {
					Square square = board.getSquares()[horizontalTo][i];
					squares.add(square);
				}
			}
			else {
				for (int i = verticalFrom-1; i >= verticalTo; i--) {
					Square square = board.getSquares()[horizontalTo][i];
					squares.add(square);
				}
			}
		}
		else if (verticalTo == verticalFrom) {
			if (horizontalFrom < horizontalTo) {
				for (int i = horizontalFrom+1; i <= horizontalTo; i++) {
					Square square = board.getSquares()[i][verticalTo];
					squares.add(square);
				}
			}
			else {
				for (int i = horizontalFrom-1; i >= horizontalTo; i--) {
					Square square = board.getSquares()[i][verticalTo];
					squares.add(square);
				}
			}
		}
		return squares;
	}
}
