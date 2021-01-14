package com.sebas.services.chess.engine;

/**
 * This is the Square class 
 * @author srevuelta
 *
 */
public class Square {
	
	private String id;
	private String color;
	private String image;
	private boolean isEmpty;
	private Piece pieza;
	private String horizontal;
	private String vertical;
	
	public boolean isEmpty() { return isEmpty; }
	public void setEmpty(boolean empty) { this.isEmpty = empty;}
	
	public Piece getPieza() { return pieza; }
	public void setPieza(Piece pieza) { this.pieza = pieza; }
	
	public String getHorizontal() {
		return horizontal;
	}
	public void setHorizontal(String horizontal) {
		this.horizontal = horizontal;
	}
	public String getVertical() {
		return vertical;
	}
	public void setVertical(String vertical) {
		this.vertical = vertical;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	

}
