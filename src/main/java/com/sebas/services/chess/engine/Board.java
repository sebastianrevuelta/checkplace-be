package com.sebas.services.chess.engine;

import org.apache.log4j.Logger;

/**
 * This is one of the most important class because it represents the board
 * @author srevuelta
 *
 */
public class Board {

	protected final static Logger log = Logger.getLogger(Board.class);
	private Square[][] squares;

	public Square[][] getSquares() { return squares; }
	public final void setSquares(Square[][] casillas) { this.squares = casillas; }

	protected Board() { }

	public Board(Square[][] squares) { 
		this.squares = UtilChess.addPieceToSquares(squares);
	}


	/**
	 * 
	 * @param j
	 * @return
	 */
	private String calculateVertical(int j) {
		String value = "";

		switch (j) {
		case 0:  value = "1";
		break;
		case 1: value = "2";
		break;
		case 2: value = "3";
		break;
		case 3: value = "4";
		break;
		case 4: value = "5";
		break;
		case 5: value = "6";
		break;
		case 6: value = "7";
		break;
		case 7: value = "8";
		break;
		default: {
			value = "1";
			break;
		}
		}
		return value;
	}


	/**
	 * 
	 * @param i
	 * @return
	 */
	private String calculateHorizontal(int i) {
		String value = "";

		switch (i) {
		case 0:  value = "a";
		break;
		case 1: value = "b";
		break;
		case 2: value = "c";
		break;
		case 3: value = "d";
		break;
		case 4: value = "e";
		break;
		case 5: value = "f";
		break;
		case 6: value = "g";
		break;
		case 7: value = "h";
		break;
		default: {
			value = "a";
			break;
		}
		}
		return value;
	}


	/**
	 * update movement
	 * @param m
	 * @param turn
	 */
	public void update(Movement m, String turn) {
		String origin = m.getOrigin();
		String destiny = m.getDestiny();

		int horOrigin = UtilChess.calculateHorizontal(origin); 
		int verOrigin = UtilChess.calculateVertical(origin);
		squares[horOrigin][verOrigin].setEmpty(true);

		int horDestiny = UtilChess.calculateHorizontal(destiny);
		int verDestiny = UtilChess.calculateVertical(destiny);

		String horizontal = destiny.substring(0,1);
		String vertical =  destiny.substring(1);
		Square square = UtilChess.createSquare(turn,horizontal,vertical, m.getPiece().getType(),m.getPiece().getColor(),false);
		squares[horDestiny][verDestiny] = square;
	}


	public void printLine(String content, String color) {
		System.out.flush();
		if ("black".equals(color)) {
			log.info(content.toLowerCase());
		}
		else {
			log.info(content.toUpperCase());
		}
	}

	/**
	 * print the board
	 */
	public void print() {
		printLine("***********************","");
		for(int j = 7; j >= 0; j--) {
			if (j != 7) printLine("","");
			for(int i= 0; i < 8; i++) {
				if (i == 0) {
					log.info("");
					printLine(" " + calculateHorizontal(j) + " ","");
				}
				if (squares[i][j].isEmpty()) {
					printLine("| ","");
				}
				else {
					Piece p = squares[i][j].getPiece();
					String type = p.getType();
					String color = p.getColor();
					if ("tower".equals(type)) {
						printLine("|T",color);
					}
					else if ("knight".equals(type)) {
						printLine("|C",color);
					}
					else if ("bishop".equals(type)) {
						printLine("|A",color);
					}
					else if ("king".equals(type)) {
						printLine("|R",color);
					}
					else if ("queen".equals(type)) {
						printLine("|D",color);
					}
					else if ("pawn".equals(type)) {
						printLine("|p",color);
					}
				}
				if (i== 7) printLine("|","");
			}
		}
		printLine("   ","");
		log.info("");
		log.info("   ");
		for(int j = 0; j < 8; j++) {
			printLine(" " + calculateVertical(j),"");
		}
		System.out.println();
	}

	public boolean checkMate(Movement m) {
		if (m.getHeuristicValue() > (double)500) {
			return true;	
		}
		return false;
	}
	public Square getSquare(String destiny) {
		int v = UtilChess.calculateVertical(destiny);
		int h = UtilChess.calculateHorizontal(destiny);
		return this.getSquares()[v][h];
	}

}
