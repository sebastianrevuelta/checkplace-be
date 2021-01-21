package com.sebas.services.chess.engine;

/**
 * This is the Square class 
 * @author srevuelta
 *
 */
public class Square {
	
	private String sqid;
	private String color;
	private String image;
	private boolean isEmpty;
	private Piece piece;
	private String horizontal;
	private String vertical;
	
	public boolean isEmpty() { return isEmpty; }
	public void setEmpty(boolean empty) { this.isEmpty = empty;}
	

	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
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

	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
