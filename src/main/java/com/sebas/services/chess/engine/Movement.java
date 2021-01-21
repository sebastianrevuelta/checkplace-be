package com.sebas.services.chess.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Movement class.
 * It propose a movement for the piece from origin to destiny
 * @author srevuelta
 *
 */
public class Movement { 

	public static String EOL = System.getProperty("line.separator");
	private Piece piece;
	private String origin;
	private String destiny;
	private int materialValue;
	private int squaresControlled;
	private double heuristicValue;
	private String description;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Piece getPiece() { return piece; }
	public void setPiece(Piece piece) {this.piece = piece;}

	public String getOrigin() { return origin; }
	public void setOrigin(String origin) { this.origin = origin; }

	public String getDestiny() { return destiny; }
	public void setDestiny(String destiny) { this.destiny = destiny; }

	public int getValue() { return materialValue; }
	public void setValue(int value) { this.materialValue = value; }

	public int getSquaresControlled() { return squaresControlled; }
	public void setSquaresControlled(int squaresControlled) { this.squaresControlled = squaresControlled; }

	public double getHeuristicValue() { return heuristicValue; }
	public void setHeuristicValue(double heuristicValue) { this.heuristicValue = heuristicValue; }

	public Movement() {}

	Movement(Piece piece, String origin, String destiny) {
		this.piece = piece;
		this.origin = origin;
		this.destiny = destiny;
		this.description = piece.getType() + origin + destiny;
	}
	
	public Movement(Board board, String move) {
		String origin = move.substring(0, 1);
		String destiny = move.substring(2, 3);
		Square[][] squares = board.getSquares();
		int h = UtilChess.calculateHorizontal(origin);
		int v = UtilChess.calculateVertical(origin);
		Square square = squares[h][v];
		Piece piece = square.getPiece();
		this.piece = piece;
		this.origin = origin;
		this.destiny = destiny;
		this.description = piece.getType() + origin + destiny;
	}


	/**
	 * make the movement
	 * @param board
	 * @param turn
	 * @return
	 */
	public Movement makeMovement(Board board, String turn, int depth) {

		Movement move;
		List<Movement> possiblesMoves = getPossiblesMoves(board,turn);
		List<Movement> realMoves = filterMoves(board,possiblesMoves,turn);
		List<Movement> realEvaluatedMoves = evaluatedMoves(board,realMoves,turn);
		List<Movement> realNextEvaluatedMoves = new ArrayList<Movement>();
		Iterator<Movement> i = realEvaluatedMoves.iterator();
		while (i.hasNext()) {
			Movement moveNext = i.next();
			Board nextBoard = copy(board);
			nextBoard.update(moveNext, turn);
			
			String nextTurn;
			if (turn.equals("white")) nextTurn = "black";
			else nextTurn = "white";
			
			List<Movement> possiblesNextOpMoves = getPossiblesMoves(nextBoard,nextTurn);
			List<Movement> realNextOpMoves = filterMoves(nextBoard,possiblesNextOpMoves,nextTurn);
			List<Movement> realNextOpEvaluatedMoves = evaluatedMoves(nextBoard,realNextOpMoves,nextTurn);
			Movement moveNextOpponent = chooseBestMove(realNextOpEvaluatedMoves);
			double value = moveNext.getHeuristicValue()-moveNextOpponent.getHeuristicValue();
			value = UtilChess.round(value,3);
			moveNext.setHeuristicValue(value);
			realNextEvaluatedMoves.add(moveNext);
			
			turn = nextTurn;
		}
		
		move = chooseBestMove(realNextEvaluatedMoves);
		if (move != null) {
			this.setPiece(move.getPiece());
			if ("pawn".equals(move.getPiece().getType())) {
				Queen queen = new Queen(turn,move.getDestiny().substring(0,1),move.getDestiny().substring(1, 2)); //TODO: User can choose what they want
				queen.setColor(turn);
				queen.setValue(9);
				if (move.getDestiny().contains("8") && "white".equals(turn)) {
					this.setPiece(queen);		
				}
				else if (move.getDestiny().contains("1") && "black".equals(turn)) {
					this.setPiece(queen);		
				}
			}
			
			this.setOrigin(move.getOrigin());
			this.setDestiny(move.getDestiny());
			this.setHeuristicValue(move.getHeuristicValue());
			this.setDescription(EOL + move.getPiece().getType() + " " + move.getOrigin() 
			+ "-" + move.getDestiny() + "(" + move.getHeuristicValue() + ")" + EOL);
		}
		return this;
	}

	private Movement evaluateNumberSquares(Board copyBoard, String turn) {
		copyBoard.update(this, turn);
		List<Movement> list = getPossiblesMoves(copyBoard,turn);
		List<Movement> listFiltered = filterMoves(copyBoard, list, turn);
		this.setSquaresControlled(listFiltered.size());
		return this;
	}
	/**
	 * getPossiblesMoves
	 * @param board
	 * @param turn
	 * @return
	 */
	private List<Movement> getPossiblesMoves(Board board, String turn) {

		List<Movement> possiblesMoves = new ArrayList<Movement>();

		Square[][] squares = board.getSquares();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square square = squares[i][j];
				if (!square.isEmpty()){
					Piece p = square.getPiece();
					if (p.getColor().equals(turn)) {
						String from = square.getHorizontal()+square.getVertical();
						List<Movement> moves = p.move(from,turn,p.getType());
						possiblesMoves.addAll(moves);
					}
				}
			}
		}
		return possiblesMoves;
	}

	/**
	 * filterMoves
	 * @param board
	 * @param turn
	 * @param possiblesMoves
	 * @return
	 */
	private List<Movement> filterMoves(Board board,	List<Movement> possiblesMoves, String turn) {

		List<Movement> realMoves = new ArrayList<Movement>();

		Iterator<Movement> i = possiblesMoves.iterator();
		while (i.hasNext()) {
			Movement move = i.next();
			Piece p = move.getPiece();
			if (p.isRealMove(move,board,turn)) {
				realMoves.add(move);
			}
		}
		return realMoves; 
	}

	/**
	 * evaluatedMoves
	 * @param board
	 * @param realMoves
	 * @param turn
	 * @return
	 */
	private List<Movement> evaluatedMoves(Board board,	List<Movement> realMoves, String turn) {

		List<Movement> evaluatedMoves = new ArrayList<Movement>();

		Iterator<Movement> i = realMoves.iterator();
		while (i.hasNext()) {
			Movement move = i.next();
			Board boardcopy = copy(board);
			move.evaluateMaterial(boardcopy, turn);
			move.evaluateNumberSquares(boardcopy, turn);
			if (move.getSquaresControlled() > 0) {
				double finalValue = (double)move.getValue() + (double)move.getSquaresControlled()/(double)100;
				finalValue = Math.round(finalValue * 100.0) / 100.0; 
				move.setHeuristicValue(finalValue);
			}
			else {
				move.setHeuristicValue(0);
			}
			evaluatedMoves.add(move);
		}

		return evaluatedMoves; 
	}

	private Board copy(Board board) {
		Board b = new Board();
		Square[][] squares = board.getSquares();
		Square[][] newSquares = new Square[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square square = squares[i][j];
				Square newSquare = new Square();
				newSquare.setEmpty(square.isEmpty());
				newSquare.setPiece(square.getPiece());
				newSquare.setHorizontal(square.getHorizontal());
				newSquare.setVertical(square.getVertical());
				newSquares[i][j] = newSquare;
			}
		}
		b.setSquares(newSquares);
		return b;

	}
	/**
	 * setValue
	 * @param b
	 * @param turn
	 * TODO
	 */
	private void evaluateMaterial(Board b, String turn) {
		int value = 0;

/*		b.update(this, turn);
		Square[][] squares = b.getSquares();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square s = squares[i][j];
				if (!s.isEmpty()) {
					if (s.getPieza().getColor().equals(turn)) {
						value += s.getPieza().getValue();
					}
					else {
						value -= s.getPieza().getValue();
					}
				}
			}

		}
*/		this.setValue(value);
	}


	/**
	 * chooseBestMove
	 * @param possiblesMoves
	 * @return
	 */
	private Movement chooseBestMove(List<Movement> possiblesMoves) {

		Collections.sort(possiblesMoves, new Comparator<Movement>() {
			public int compare(Movement o1, Movement o2) {
				return new Double(o1.getHeuristicValue()).compareTo(new Double(o2.getHeuristicValue()));
			}
		});

		if (possiblesMoves.size() > 0) {
			return possiblesMoves.get(possiblesMoves.size()-1);
		}
		return null;
	}
}
