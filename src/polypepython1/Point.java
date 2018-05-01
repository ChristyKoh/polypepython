package polypepython1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Point {
	public final static int SIZE = 8;
	protected int x;
	protected int y;
	private int xdir;
	private int ydir;
	Color color;
	
	public Point(){
		x = 0;
		y = 0;
		xdir = 0;
		ydir = 0;
		color = Color.black;
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		xdir = 0;
		ydir = 0;
		color = Color.black;
	}
	public Point(int x, int y, Color c) {
		this.x = x;
		this.y = y;
		xdir = 1; //initially traveling right
		ydir = 0;
		this.color = c;
	}
	public Point(int x, int y, int xd, int yd, Color c) {
		this.x = x;
		this.y = y;
		this.xdir = xd;
		this.ydir = yd;
		this.color = c;
	}
	
	public void setX(int x){
		this.x = x;
	}
	public int getX(){
		return this.x;
	}

	public void setY(int y){
		this.y = y;
	}
	public int getY(){
		return this.y;
	}
	
	public void setYDir(int d){
		this.ydir = d;
	}
	public int getYDir() {
		return this.ydir;
	}

	public void setXDir(int d){
		this.xdir = d;
	}
	public int getXDir() {
		return this.xdir;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	public Color getColor() {
		return this.color;
	}
	
	/*
	 * Draws a point as a filled circle.
	 */
	public void draw(Graphics g2d) {
		g2d.setColor(color); //set to color of amino acid
		g2d.fillOval(x, y, SIZE, SIZE);
	}
	
	/*
	 * Increments x and y values of points based on the direction each 
	 * segment of the snake is travelling.
	 */
	public void step() {
		this.setX(this.getX()+this.getXDir()*SIZE);
		this.setY(this.getY()+this.getYDir()*SIZE);
	}
	
	public boolean equals(int x, int y) {
		return (this.x == x && this.y == y);
	}
}
