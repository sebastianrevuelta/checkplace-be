package com.sebas.services.chess.engine;

import org.apache.log4j.Logger;
/**
 * This is the Match class
 * @author srevuelta
 *
 */
public class Match {

	private static final long TIME_OUT_THINKING = 100;
	private final static Logger log = Logger.getLogger(Match.class);

	private Board board;
	private String turn;
	private boolean checkmate;
	private int movement;
	private String history;
	private String logger;
	private String player1;
	private String player2;
	private String timer;

	protected Match() { }
	
	Match(Board board, String turn, boolean checkmate, int movement, String history, 
			String logger, String player1, String player2, String timer) {
		this.board = board;
		this.turn = turn;
		this.checkmate = checkmate;
		this.movement = movement;
		this.history = history;
		this.logger = logger;
		this.player1 = player1;
		this.player2 = player2;
		this.timer = timer;
	}
	public String getPlayer1() { return player1; }
	public void setPlayer1(String player1) { this.player1 = player1; }
	
	public String getPlayer2() { return player2; }
	public void setPlayer2(String player2) { this.player2 = player2; }
	
	public String getTimer() { 	return timer; }
	public void setTimer(String timer) { this.timer = timer; }
	
	public String getLog() { return logger; }
	public void setLog(String log) { this.logger = log; }
	
	public Board getBoard() { return board; }
	public final void setBoard(Board board) { this.board = board; }

	public String getTurn() { return turn; }
	public final void setTurn(String turn) { this.turn = turn; }

	public String getHistory() { return history; }
	public final void setHistory(String history) { this.history = history; }

	public static void main(String[] args) {
		Match match = new Match();
		match.setTurn("white");
		match.startGame();
	}
	/**
	 * start the game
	 * @date August 2019
	 */     
	public void startGame() {
		String result = "";
		//StringBuilder sb = new StringBuilder();
		Board board = new Board();
		try { 
			while (!checkmate) {
				board.print();
				Movement m = new Movement();
				m = m.makeMovement(board,turn,1);
				checkmate = board.checkMate(m);
				board.update(m,turn);
				
				result += m.getPiece();
				//sb.append(m.getPiece());
				
				if (turn.equals("white")) setTurn("black");
				else setTurn("white");

				Thread.sleep(TIME_OUT_THINKING);  
			}
			System.out.println("Match: " + result);
			System.out.println("Match: " + result);
		}
		catch (InterruptedException e) { 
			log.error("Error in the thread of the match");
			 System.exit(0);
		}
	}
	
	/**
	 * updateBoard
	 * @param m
	 */
	public void updateBoard(Movement m) {
		String origin = m.getOrigin();
		String destiny = m.getDestiny();
		
		int horOrigin = UtilChess.calculateHorizontal(origin); 
		int verOrigin = UtilChess.calculateVertical(origin);
		this.getBoard().getSquares()[horOrigin][verOrigin].setEmpty(true);
		this.getBoard().getSquares()[horOrigin][verOrigin].setImage("");

		int horDestiny = UtilChess.calculateHorizontal(destiny);
		int verDestiny = UtilChess.calculateVertical(destiny);

		String horizontal = destiny.substring(0,1);
		String vertical =  destiny.substring(1);

		Square square = UtilChess.createSquare(this.getTurn(),horizontal,vertical, m.getPiece().getType(),m.getPiece().getColor(),false);
		this.getBoard().getSquares()[horDestiny][verDestiny] = square;
	}
	
	/**
	 * move!
	 * @return
	 */
	public String justMove(Movement m) {

		if (m == null) {
		  m = new Movement();
		  m = m.makeMovement(board,turn,1);
		}
		checkmate = board.checkMate(m);
		String descriptionMove;

		//boolean checkMate = board.checkMovement(m);
		board.update(m,turn);

		if ("white".equals(turn)) { 
			setTurn("black"); 
			if ("pawn".equals(m.getPiece().getType())) {
				descriptionMove = getHistory() + "\n" + movement + "." + m.getDestiny() + " ("+ m.getHeuristicValue() + ")";
			}
			else {
				descriptionMove = getHistory() + "			" + movement + "." + m.getPiece().getType().toUpperCase().charAt(0) + m.getDestiny() + " ("+ m.getHeuristicValue() + ")";
			}
		}
		else { 
			setTurn("white");
			if ("pawn".equals(m.getPiece().getType())) {
				descriptionMove = getHistory() + " | " + m.getDestiny() + " ("+ m.getHeuristicValue() + ")";
			}
			else {
				descriptionMove = getHistory() + " | " + m.getPiece().getType().toUpperCase().charAt(0) + m.getDestiny() + " ("+ m.getHeuristicValue() + ")";
			}
			movement++;
		}
		setHistory(descriptionMove);
		if (checkmate) {
			descriptionMove += "++";
		}

		return getHistory();
	}

}
