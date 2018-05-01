package polypepython1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Polypeptide{
	
	private ArrayList<Amino> polyPoints = new ArrayList<Amino>();
	final int STARTX = 100, STARTY = 100, SIZE = 4;
	private Point head;
	
	public Polypeptide() {
		//add head
		head = new Point(STARTX, STARTY, Color.orange);
	}
	
	public ArrayList<Amino> get() {
		return polyPoints;
	}
	
	public int getXDir() {return head.getXDir();}
	
	public void setXDir(int x) {head.setXDir(x);}
	
	public int getYDir() {return head.getYDir();}
	
	public void setYDir(int y) {head.setYDir(y);}
	
	public void add(Amino a) {polyPoints.add(a);}
	
	public void draw(Graphics2D g2d) {
		System.out.println("drawing head at " + head.getX() + " " + head.getY());
		g2d.drawOval(head.getX(), head.getY(), SIZE, SIZE); //draw head
		head.step();
		
		for(int i=0; i<polyPoints.size(); i++) { //draw amino acids
			Amino a = polyPoints.get(i);
			a.draw(g2d);
			a.step();
			if(i == 0) { //update x and y directions
				a.setXDir(head.getXDir());
				a.setYDir(head.getYDir());
			} else {
				a.setXDir(polyPoints.get(i-1).getXDir());
				a.setYDir(polyPoints.get(i-1).getYDir());
			}
		}
	}
}
