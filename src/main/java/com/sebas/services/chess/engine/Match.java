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
	private String historyMatch;
	private String logger;
	private String player1;
	private String player2;
	private String timer;

	protected Match() { }
	
	Match(Board board, String turn, boolean checkmate, int movement, String historyMatch, 
			String logger, String player1, String player2, String timer) {
		this.board = board;
		this.turn = turn;
		this.checkmate = checkmate;
		this.movement = movement;
		this.historyMatch = historyMatch;
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

	public String getTurno() { return turn; }
	public final void setTurno(String turno) { this.turn = turno; }

	public String getHistoryMatch() { return historyMatch; }
	public final void setHistoryMatch(String historyMatch) { this.historyMatch = historyMatch; }

	public Match(String turno) {
		setBoard(new Board());
		setTurno(turno);
		movement = 1;
		setHistoryMatch("");
	}

	public static void main(String[] args) {
		Match match = new Match("white");
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
				
				if (turn.equals("white")) setTurno("black");
				else setTurno("white");

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
			setTurno("black"); 
			if ("pawn".equals(m.getPiece().getType())) {
				descriptionMove = getHistoryMatch() + "\n" + movement + "." + m.getDestiny() + " ("+ m.getHeuristicValue() + ")";
			}
			else {
				descriptionMove = getHistoryMatch() + "			" + movement + "." + m.getPiece().getType().toUpperCase().charAt(0) + m.getDestiny() + " ("+ m.getHeuristicValue() + ")";
			}
		}
		else { 
			setTurno("white");
			if ("pawn".equals(m.getPiece().getType())) {
				descriptionMove = getHistoryMatch() + " | " + m.getDestiny() + " ("+ m.getHeuristicValue() + ")";
			}
			else {
				descriptionMove = getHistoryMatch() + " | " + m.getPiece().getType().toUpperCase().charAt(0) + m.getDestiny() + " ("+ m.getHeuristicValue() + ")";
			}
			movement++;
		}
		setHistoryMatch(descriptionMove);
		if (checkmate) {
			descriptionMove += "++";
		}

		return getHistoryMatch();
	}

}
