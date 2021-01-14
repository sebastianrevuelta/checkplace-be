package com.sebas.services.chess.engine;

/**
 * Utility class
 * @author srevuelta
 *
 */
public class UtilChess {

	/**
	 * 
	 * @param coord
	 * @return
	 */
	public static int calculateHorizontal(String coord) {
		char letter = coord.charAt(0);

		int value = 0;

		switch (letter) {
		case 'a':  value = 0;
		break;
		case 'b': value = 1;
		break;
		case 'c': value = 2;
		break;
		case 'd': value = 3;
		break;
		case 'e': value = 4;
		break;
		case 'f': value = 5;
		break;
		case 'g': value = 6;
		break;
		case 'h': value = 7;
		break;
		default: {
			value = 0;
			break;
		}
		}
		return value;		

	}

	public static String calculateHorizontal(int column) {


		String value = "a";

		switch (column) {
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
	 * 
	 * @param coord
	 * @return
	 */
	public static int calculateVertical(String coord) {
		char number = coord.charAt(1);

		int value = 0;

		switch (number) {
		case '1':  value = 0;
		break;
		case '2': value = 1;
		break;
		case '3': value = 2;
		break;
		case '4': value = 3;
		break;
		case '5': value = 4;
		break;
		case '6': value = 5;
		break;
		case '7': value = 6;
		break;
		case '8': value = 7;
		break;
		default: {
			value = 0;
			break;
		}
		}
		return value;		

	}

	public static String calculateVertical(int value) {

		String row = "0";

		switch (value) {
		case 0: row = "1";
		break;
		case 1: row = "2";
		break;
		case 2: row = "3";
		break;
		case 3: row = "4";
		break;
		case 4: row = "5";
		break;
		case 5: row = "6";
		break;
		case 6: row = "7";
		break;
		case 7: row = "8";
		break;
		default: {
			row = "1";
			break;
		}
		}
		return row;		

	}

	/**
	 * 
	 * @return
	 */
	public static String randomPiece(int value) {

		String piece;
		switch (value) {

		case 0: piece = "pawn";
		break;

		case 1: piece = "knight";
		break;

		case 2: piece = "bishop";
		break;
		
		case 3: piece = "tower";
		break;
		
		case 4: piece = "queen";
		break;
		
		case 5: piece = "king";
		break;
		
		default: piece = "pawn";
		break;
		}
		return piece;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
