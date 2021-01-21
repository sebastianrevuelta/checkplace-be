package com.sebas.services.chess.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the Pawn class 
 * @author srevuelta
 *
 */
public class Pawn extends Piece {

	public Pawn() {
		super.setType("pawn");
		super.setValue(1);
	}

	/**
	 * move Pawn
	 * @return
	 */
	public List<Movement> move(String from, String turn, String type) {

		List<Movement> possiblesMoves = new ArrayList<Movement>();
		String to;

		char column = from.charAt(0);
		char row = from.charAt(1);

		int valueRow = UtilChess.calculateVertical(from);
		int valueCol = UtilChess.calculateHorizontal(from);

		String rowdestiny = "";
		if ("white".equals(turn)) { 
			//only one step 
			int rowMove = new Integer(valueRow+1).intValue();
			rowdestiny = UtilChess.calculateVertical(rowMove);
			to = column + rowdestiny;
			Movement move1 = new Movement(this,from,to);
			possiblesMoves.add(move1);

			//could be two steps at the beginning
			if (row == '2') {
				rowMove = new Integer(valueRow+2).intValue();
				rowdestiny = UtilChess.calculateVertical(rowMove);
				to = column + rowdestiny;
				Movement move2 = new Movement(this,from,to);
				possiblesMoves.add(move2);
			}
			
			//could be lunch
			rowMove = new Integer(valueRow+1).intValue();
			int colMove = new Integer(valueCol+1).intValue();
			if (colMove < 8 && colMove >= 0) {
				rowdestiny = UtilChess.calculateVertical(rowMove);
				String columnDestiny = UtilChess.calculateHorizontal(colMove);
				to = columnDestiny + rowdestiny;
				Movement move2 = new Movement(this,from,to);
				possiblesMoves.add(move2);
			}
			colMove = new Integer(valueCol-1).intValue();
			if (colMove < 8 && colMove >= 0) {
				rowdestiny = UtilChess.calculateVertical(rowMove);
				String columnDestiny = UtilChess.calculateHorizontal(colMove);
				to = columnDestiny + rowdestiny;
				Movement move2 = new Movement(this,from,to);
				possiblesMoves.add(move2);
			}
		}
		if ("black".equals(turn)) {
			//only one step
			int rowMove = new Integer(valueRow-1).intValue();
			rowdestiny = UtilChess.calculateVertical(rowMove);
			to = column + rowdestiny;
			Movement move3 = new Movement(this,from,to);
			possiblesMoves.add(move3);			
			//could be two steps at the beginning
			if (row == '7') {
				rowMove = new Integer(valueRow-2).intValue();
				rowdestiny = UtilChess.calculateVertical(rowMove);
				to = column + rowdestiny;
				Movement move4 = new Movement(this,from,to);
				possiblesMoves.add(move4);			
			}
			//could be lunch
			rowMove = new Integer(valueRow-1).intValue();
			int colMove = new Integer(valueCol+1).intValue();
			if (colMove < 8 && colMove >= 0) {
				rowdestiny = UtilChess.calculateVertical(rowMove);
				String columnDestiny = UtilChess.calculateHorizontal(colMove);
				to = columnDestiny + rowdestiny;
				Movement move2 = new Movement(this,from,to);
				possiblesMoves.add(move2);
			}
			colMove = new Integer(valueCol-1).intValue();
			if (colMove < 8 && colMove >= 0) {
				rowdestiny = UtilChess.calculateVertical(rowMove);
				String columnDestiny = UtilChess.calculateHorizontal(colMove);
				to = columnDestiny + rowdestiny;
				Movement move2 = new Movement(this,from,to);
				possiblesMoves.add(move2);
			}
		}
		return possiblesMoves;
	}

	/**
	 * check if the move is possible
	 */
	public boolean isRealMove(Movement movement, Board board, String turn) {
		
		Square[][] squares = board.getSquares();

		String from = movement.getOrigin();
		String to = movement.getDestiny();

		int verticalFrom = UtilChess.calculateVertical(from);
		int horizontalFrom = UtilChess.calculateHorizontal(from);

		int horizontalTo = UtilChess.calculateHorizontal(to);
		int verticalTo = UtilChess.calculateVertical(to);

		int steps = verticalTo - verticalFrom;
		if (steps == 2) { //it means two steps from the original white square
			Square square = squares[horizontalTo][verticalTo];
			if (!square.isEmpty()) return false;
		}
		if (steps == -2) { //it means two steps from the original black square
			Square square = squares[horizontalTo][verticalTo];
			if (!square.isEmpty()) return false;
		}		

		if (horizontalTo != horizontalFrom) { //it is a lunch
			Square square = squares[horizontalTo][verticalTo];
			if (square.isEmpty()) return false;
			if (square.getPiece().getColor().equals(turn)) return false;
		}
		else { //normal movement: one step
			Square square = squares[horizontalTo][verticalTo];
			if (!square.isEmpty()) return false;
		}


		return true;
	}
}
