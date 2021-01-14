package com.sebas.services.chess.engine;

import java.util.List;

import org.apache.log4j.Logger;
/**
 * This is the Piece class 
 * @author srevuelta
 *
 */
public abstract class Piece {

	private String color;
	private String horizontal;
	private String vertical;
	private String type;
	private int value;
	
	protected final static Logger log = Logger.getLogger(Piece.class);
	
	public int getValue() { return value; }
	public final void setValue(int value) { this.value = value; }
	
	public String getType() { return type; }
	public final void setType(String type) { this.type = type; }
	
	public String getColor() { return color; }
	public void setColor(String color) { this.color = color; }
	
	public String getHorizontal() { return horizontal; }
	public void setHorizontal(String horizontal) { this.horizontal = horizontal; }
	
	public String getVertical() { return vertical; }
	public void setVertical(String vertical) { this.vertical = vertical; }

	public abstract List<Movement> move(String from, String turn, String piece);

	public abstract boolean isRealMove(Movement movement, Board board, String turn);
}
