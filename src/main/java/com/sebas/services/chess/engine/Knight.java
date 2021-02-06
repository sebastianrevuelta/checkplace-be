package com.sebas.services.chess.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the Knight class
 * @author srevuelta
 *
 */
public class Knight extends Piece {
	
	public Knight() {
		super.setType("knight");
		super.setValue(3);
	}
	
	/***
	 * move the knight
	 * @param turn
	 * @return
	 */
	public List<Movement> move(String from, String turn, String type) {

		log.info(turn + " " + type + " is going to move from: " + from);
		List<Movement> possiblesMoves = new ArrayList<Movement>();

		String to;

		String rowdestiny = "a";
		String columndestiny = "1";

		int valueColumn = UtilChess.calculateHorizontal(from);
		int valueRow = UtilChess.calculateVertical(from);
		this.setColor(turn);
		if (valueRow+2 < 8) {
			int row = new Integer(valueRow+2).intValue();
			rowdestiny = UtilChess.calculateVertical(row);
			if (valueColumn+1 < 8) {
				int column = new Integer(valueColumn+1).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move1 = new Movement(this,from,to);
				possiblesMoves.add(move1);
			}
			else if (valueColumn-1 >= 0) {
				int column = new Integer(valueColumn-1).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move1 = new Movement(this,from,to);
				possiblesMoves.add(move1);
			}
		}
		
		if (valueRow+1 < 8) {
			int row = new Integer(valueRow+1).intValue();
			rowdestiny = UtilChess.calculateVertical(row);
			if (valueColumn+2 < 8) {
				int column = new Integer(valueColumn+2).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move2 = new Movement(this,from,to);
				possiblesMoves.add(move2);	
			}
			else if (valueColumn-2 >= 0) {
				int column = new Integer(valueColumn-2).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move2 = new Movement(this,from,to);
				possiblesMoves.add(move2);	
			}
				
		}

		if (valueRow-2 >= 0) {
			int row = new Integer(valueRow-2).intValue();
			rowdestiny = UtilChess.calculateVertical(row);
			if (valueColumn+1 < 8) {
				int column = new Integer(valueColumn+1).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move3 = new Movement(this,from,to);
				possiblesMoves.add(move3);	
			}
			else if (valueColumn-1 >= 0) {
				int column = new Integer(valueColumn-1).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;	
				Movement move3 = new Movement(this,from,to);
				possiblesMoves.add(move3);	
			}
		}
		
		if (valueRow-1 < 8 && valueRow-1 >= 0) {
			int row = new Integer(valueRow-1).intValue();
			rowdestiny = UtilChess.calculateVertical(row);
			if (valueColumn+2 < 8) {
				int column = new Integer(valueColumn+2).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;
				Movement move4 = new Movement(this,from,to); //TODO: Error from b8 to d1
				possiblesMoves.add(move4);	
			}
			else if (valueColumn-2 >= 0) {
				int column = new Integer(valueColumn-2).intValue();
				columndestiny = UtilChess.calculateHorizontal(column);
				to = columndestiny + rowdestiny;
				Movement move4 = new Movement(this,from,to);
				possiblesMoves.add(move4);	
			}
	
		}

		return possiblesMoves;
	}
	
	/**
	 * check if the move is possible
	 */
	public boolean isRealMove(Movement movement, Board board, String turn) {
		
		String to = movement.getDestiny();
		
		int horizontalTo = UtilChess.calculateHorizontal(to);
		int verticalTo = UtilChess.calculateVertical(to);

		Square[][] squares = board.getSquares();
		Square square = squares[horizontalTo][verticalTo];
		if (!square.isEmpty()) {
			if (turn.equals(square.getPiece().getColor())) return false;
		}
		
		return true;
	}
	
}
