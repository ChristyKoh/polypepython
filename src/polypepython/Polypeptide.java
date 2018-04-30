package polypepython;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import polypepython1.Point;

public class Polypeptide {

	ArrayList<Point> aminoPoints;
	int xDir, yDir;
	boolean isMoving, elongate;
	final int STARTSIZE = 10, STARTX = 150, STARTY = 150;
	
	public Polypeptide(){
		aminoPoints = new ArrayList<Point>();
		xDir = 0;
		yDir = 0;
		isMoving = false;
		elongate = false;
		aminoPoints.add(new Point(STARTX, STARTY, Color.black));
		for(int i=1; i<STARTSIZE; i++){
			aminoPoints.add(new Point(STARTX-i*4, STARTY, Color.black));
		}
		//System.out.println(aminoPoints.size());
	}
	
	public void draw(Graphics g){
		g.setColor(Color.black);
		for(Point p: aminoPoints){
			g.setColor(p.getColor());
			g.drawOval(p.getX(), p.getY(), 4, 4);
			//g.fillRect(p.getX(), p.getY(), 4, 4);
		}
		
	}
	
	public int getXDir(){
		return xDir;
	}
	
	public int getYDir(){
		return yDir;
	}
	
	public void setXDir(int x){
		xDir = x;
	}
	
	public void setYDir(int y){
		yDir = y;
	}
	
	// X position of head
	public int getX(){
		return aminoPoints.get(0).getX();
	}
	
	public int getY(){
		return aminoPoints.get(0).getY();
	}
}
