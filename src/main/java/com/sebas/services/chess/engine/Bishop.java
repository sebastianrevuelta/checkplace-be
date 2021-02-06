package com.sebas.services.chess.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is the bishop class
 * @author srevuelta August 2019
 *   
 */
public class Bishop extends Piece {
	
	public Bishop() {
		super.setType("bishop");
		super.setValue(3);
	}  

	/***
	 * move the bishop
	 * @param turn
	 * @return
	 */
	@Override
	public List<Movement> move(String from, String turn, String type) {

		log.info(turn + " " + type + " is going to move from: " + from);
		List<Movement> possibleMoves = new ArrayList<Movement>();

		String to;

		String rowdestiny = "a";
		String columndestiny = "1";

		int valueColumn = UtilChess.calculateHorizontal(from);
		int valueRow = UtilChess.calculateVertical(from);
		this.setColor(turn);
		for (int i=1; i < 8; i++) {
			if (valueRow+i < 8) {
				int row = valueRow+i;
				rowdestiny = UtilChess.calculateVertical(row);
				if (valueColumn+i < 8) {
					int column = valueColumn+i;
					columndestiny = UtilChess.calculateHorizontal(column);
					to = columndestiny + rowdestiny;	
					Movement move1 = new Movement(this,from,to);
					possibleMoves.add(move1);	
				}
				else if (valueColumn-i >= 0) {
					int column = valueColumn-i;
					columndestiny = UtilChess.calculateHorizontal(column);
					to = columndestiny + rowdestiny;	
					Movement move1 = new Movement(this,from,to);
					possibleMoves.add(move1);	
				}

			}

			if (valueRow-i >=0) {
				int row = valueRow-i;
				rowdestiny = UtilChess.calculateVertical(row);
				if (valueColumn+i < 8) {
					int column = valueColumn+i;
					columndestiny = UtilChess.calculateHorizontal(column);
					to = columndestiny + rowdestiny;	
					Movement move2 = new Movement(this,from,to);
					possibleMoves.add(move2);	
				}
				else if (valueColumn-i >= 0) {
					int column = valueColumn-i;
					columndestiny = UtilChess.calculateHorizontal(column);
					to = columndestiny + rowdestiny;	
					Movement move2 = new Movement(this,from,to);
					possibleMoves.add(move2);	
				}
				
			}
		}
		return possibleMoves;
	}

	public boolean isRealMove(Movement movement, Board board, String turn) {

		List<Square> squares = getSquares(board, movement);
		Iterator<Square> i = squares.iterator();
		while (i.hasNext()) {
			Square square = i.next();
			if (!square.isEmpty()) {
				Piece p = square.getPiece();
				if (p != null) {
					if (turn.equals(square.getPiece().getColor())) {
						return false;
					}
					else { //it it different color
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

	public List<Square> getSquares(Board board, Movement movement) {
		List<Square> squares = new ArrayList<Square>();

		String from = movement.getOrigin();
		String to = movement.getDestiny();

		int horizontalTo = UtilChess.calculateHorizontal(to);
		int verticalTo = UtilChess.calculateVertical(to);

		int horizontalFrom = UtilChess.calculateHorizontal(from);
		int verticalFrom = UtilChess.calculateVertical(from);

		if (horizontalFrom < horizontalTo) {
			if (verticalFrom < verticalTo) {
				int j = verticalFrom+1;
				for (int i = horizontalFrom+1; i <= horizontalTo; i++) {
					Square square = board.getSquares()[i][j];
					squares.add(square);
					j++;
				}
			}
			else {
				int j = verticalFrom-1;
				for (int i = horizontalFrom+1;i <= horizontalTo; i++) {
					if (j < 0) break;
					Square square = board.getSquares()[i][j];
					squares.add(square);
					j--;
				}
			}
		}
		else {
			if (verticalFrom < verticalTo) {
				int j = verticalFrom+1;
				for (int i = horizontalFrom-1; i >= horizontalTo; i--) {
					Square square = board.getSquares()[i][j];
					squares.add(square);
					j++;
				}
			}
			else {
				int j = verticalFrom-1;
				for (int i = horizontalFrom-1; i >= horizontalTo && j >=0; i--) {
					Square square = board.getSquares()[i][j];
					squares.add(square);
					j--;
				}
			}
		}

		return squares;

	}

}
