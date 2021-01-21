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

	public Board() { 
		setSquares(buildInitialPosition());
	}

	public Board(Square[][] squares) { 
		this.squares = squares;
	}
	
	/**
	 * build the start position
	 * @return
	 */
	private Square[][] buildInitialPosition() {
		Square[][] casillas = new Square[8][8];

		//fill board

		//white pieces
		Square squarea1 = UtilChess.createSquare("black","a","1","tower","white",false);
		casillas[0][0] = squarea1;

		Square squareb1 = UtilChess.createSquare("white","b","1","knight","white",false);
		casillas[1][0] = squareb1;

		Square squarec1 = UtilChess.createSquare("black","c","1","bishop","white",false);
		casillas[2][0] = squarec1;

		Square squared1 = UtilChess.createSquare("white","d","1","queen","white",false);
		casillas[3][0] = squared1;

		Square squaree1 = UtilChess.createSquare("black","e","1","king","white",false);
		casillas[4][0] = squaree1;

		Square squaref1 = UtilChess.createSquare("white","f","1","bishop","white",false);
		casillas[5][0] = squaref1;

		Square squareg1 = UtilChess.createSquare("black","g","1","knight","white",false);
		casillas[6][0] = squareg1;

		Square squareh1 = UtilChess.createSquare("white","h","1","tower","white",false);
		casillas[7][0] = squareh1;

		//white pawns
		Square squarea2 = UtilChess.createSquare("white","a","2","pawn","white",false);
		casillas[0][1] = squarea2;
		Square squareb2 = UtilChess.createSquare("black","b","2","pawn","white",false);
		casillas[1][1] = squareb2;
		Square squarec2 = UtilChess.createSquare("white","c","2","pawn","white",false);
		casillas[2][1] = squarec2;
		Square squared2 = UtilChess.createSquare("black","d","2","pawn","white",false);
		casillas[3][1] = squared2;
		Square squaree2 = UtilChess.createSquare("white","e","2","pawn","white",false);
		casillas[4][1] = squaree2;
		Square squaref2 = UtilChess.createSquare("black","f","2","pawn","white",false);
		casillas[5][1] = squaref2;
		Square squareg2 = UtilChess.createSquare("white","g","2","pawn","white",false);
		casillas[6][1] = squareg2;
		Square squareh2 = UtilChess.createSquare("black","h","2","pawn","white",false);
		casillas[7][1] = squareh2;
		
		//
		Square squarea3 = UtilChess.createSquare("black","a","3","","",true);
		casillas[0][2] = squarea3;
		Square squareb3 = UtilChess.createSquare("white","b","3","","",true);
		casillas[1][2] = squareb3;
		Square squarec3 = UtilChess.createSquare("black","c","3","","",true);
		casillas[2][2] = squarec3;
		Square squared3 = UtilChess.createSquare("white","d","3","","",true);
		casillas[3][2] = squared3;
		Square squaree3 = UtilChess.createSquare("black","e","3","","",true);
		casillas[4][2] = squaree3;
		Square squaref3 = UtilChess.createSquare("white","f","3","","",true);
		casillas[5][2] = squaref3;
		Square squareg3 = UtilChess.createSquare("black","g","3","","",true);
		casillas[6][2] = squareg3;
		Square squareh3 = UtilChess.createSquare("white","h","3","","",true);
		casillas[7][2] = squareh3;
		
		Square squarea4 = UtilChess.createSquare("white","a","4","","",true);
		casillas[0][3] = squarea4;
		Square squareb4 = UtilChess.createSquare("black","b","4","","",true);
		casillas[1][3] = squareb4;
		Square squarec4 = UtilChess.createSquare("white","c","4","","",true);
		casillas[2][3] = squarec4;
		Square squared4 = UtilChess.createSquare("black","d","4","","",true);
		casillas[3][3] = squared4;
		Square squaree4 = UtilChess.createSquare("white","e","4","","",true);
		casillas[4][3] = squaree4;
		Square squaref4 = UtilChess.createSquare("black","f","4","","",true);
		casillas[5][3] = squaref4;
		Square squareg4 = UtilChess.createSquare("white","g","4","","",true);
		casillas[6][3] = squareg4;
		Square squareh4 = UtilChess.createSquare("black","h","4","","",true);
		casillas[7][3] = squareh4;
		
		
		Square squarea5 = UtilChess.createSquare("black","a","5","","",true);
		casillas[0][4] = squarea5;
		Square squareb5 = UtilChess.createSquare("white","b","5","","",true);
		casillas[1][4] = squareb5;
		Square squarec5 = UtilChess.createSquare("black","c","5","","",true);
		casillas[2][4] = squarec5;
		Square squared5 = UtilChess.createSquare("white","d","5","","",true);
		casillas[3][4] = squared5;
		Square squaree5 = UtilChess.createSquare("black","e","5","","",true);
		casillas[4][4] = squaree5;
		Square squaref5 = UtilChess.createSquare("white","f","5","","",true);
		casillas[5][4] = squaref5;
		Square squareg5 = UtilChess.createSquare("black","g","5","","",true);
		casillas[6][4] = squareg5;
		Square squareh5 = UtilChess.createSquare("white","h","5","","",true);
		casillas[7][4] = squareh5;
		
		Square squarea6 = UtilChess.createSquare("white","a","6","","",true);
		casillas[0][5] = squarea6;
		Square squareb6 = UtilChess.createSquare("black","b","6","","",true);
		casillas[1][5] = squareb6;
		Square squarec6 = UtilChess.createSquare("white","c","6","","",true);
		casillas[2][5] = squarec6;
		Square squared6 = UtilChess.createSquare("black","d","6","","",true);
		casillas[3][5] = squared6;
		Square squaree6 = UtilChess.createSquare("white","e","6","","",true);
		casillas[4][5] = squaree6;
		Square squaref6 = UtilChess.createSquare("black","f","6","","",true);
		casillas[5][5] = squaref6;
		Square squareg6 = UtilChess.createSquare("white","g","6","","",true);
		casillas[6][5] = squareg6;
		Square squareh6 = UtilChess.createSquare("black","h","6","","",true);
		casillas[7][5] = squareh6;

		//black pieces
		Square squarea8 = UtilChess.createSquare("white","a","8","tower","black",false);
		casillas[0][7] = squarea8;

		Square squareb8 = UtilChess.createSquare("black","b","8","knight","black",false);
		casillas[1][7] = squareb8;

		Square squarec8 = UtilChess.createSquare("white","c","8","bishop","black",false);
		casillas[2][7] = squarec8;

		Square squared8 = UtilChess.createSquare("black","d","8","queen","black",false);
		casillas[3][7] = squared8;

		Square squaree8 = UtilChess.createSquare("white","e","8","king","black",false);
		casillas[4][7] = squaree8;

		Square squaref8 = UtilChess.createSquare("black","f","8","bishop","black",false);
		casillas[5][7] = squaref8;

		Square squareg8 = UtilChess.createSquare("white","g","8","knight","black",false);
		casillas[6][7] = squareg8;

		Square squareh8 = UtilChess.createSquare("black","h","8","tower","black",false);
		casillas[7][7] = squareh8;

		//black pawns
		Square squarea7 = UtilChess.createSquare("black","a","7","pawn","black",false);
		casillas[0][6] = squarea7;
		Square squareb7 = UtilChess.createSquare("white","b","7","pawn","black",false);
		casillas[1][6] = squareb7;
		Square squarec7 = UtilChess.createSquare("black","c","7","pawn","black",false);
		casillas[2][6] = squarec7;
		Square squared7 = UtilChess.createSquare("white","d","7","pawn","black",false);
		casillas[3][6] = squared7;
		Square squaree7 = UtilChess.createSquare("black","e","7","pawn","black",false);
		casillas[4][6] = squaree7;
		Square squaref7 = UtilChess.createSquare("white","f","7","pawn","black",false);
		casillas[5][6] = squaref7;
		Square squareg7 = UtilChess.createSquare("black","g","7","pawn","black",false);
		casillas[6][6] = squareg7;
		Square squareh7 = UtilChess.createSquare("white","h","7","pawn","black",false);
		casillas[7][6] = squareh7;


//		//empty board
//		for(int i = 0; i < 8; i++) {
//			for(int j = 0; j < 8; j++) {
//				Square square = casillas[i][j];
//				if (square == null) {
//					square = new Square();
//					square.setEmpty(true);
//					String horizontal = calculateHorizontal(i);
//					square.setHorizontal(horizontal);
//					String vertical = calculateVertical(j);
//					square.setVertical(vertical);
//					casillas[i][j] = square;
//				}
//			}
//		}


		return casillas;
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
		System.err.println(m);
		System.err.println(m.getDescription());
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
