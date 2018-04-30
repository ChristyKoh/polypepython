package polypepython1;

import java.awt.Color;

public class Point {
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
		color = Color.white;
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		xdir = 1; //initially traveling right
		ydir = 0;
		color = Color.white;
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
		this.xdir = xd; //initially traveling right
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
	
	public void step() {
		this.setX(this.getX()+this.getXDir()*4);
		this.setY(this.getY()+this.getYDir()*4);
	}
	
	public boolean equals(int x, int y) {
		return (this.x == x && this.y == y);
	}
}
