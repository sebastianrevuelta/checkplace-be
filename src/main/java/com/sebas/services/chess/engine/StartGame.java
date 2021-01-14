package com.sebas.services.chess.engine;

/**
 * All starts here
 * @author srevuelta
 *
 */
public class StartGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start!");
		Match match = new Match("white");
		match.startGame();
	}

}
