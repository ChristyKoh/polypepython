package polypepython1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Polypeptide{
	
	private ArrayList<Amino> polyPoints = new ArrayList<Amino>();
	final int SIZE = 8, PADDING = 50, STARTX = SIZE + PADDING, STARTY = SIZE + PADDING;
	private Point head;
	
	public Polypeptide() {
		//add head
		head = new Point(STARTX, STARTY, Color.orange);
		//this.add(new Amino("methionine"));
		//this.add(new Amino("asparagine"));
	}
	
	public ArrayList<Amino> get() {
		return polyPoints;
	}
	
	public int getX() {return head.getX();}
	
	public int getY() {return head.getY();}
	
	public int getXDir() {return head.getXDir();}
	
	public void setXDir(int x) {head.setXDir(x);}
	
	public int getYDir() {return head.getYDir();}
	
	public void setYDir(int y) {head.setYDir(y);}
	
	public void add(Amino a) {polyPoints.add(a);}
	
	public boolean hasCollision(int w, int h) {
		if(head.getX() < 0 || head.getY() < 0) {
			return true;
		}
		if(head.getX() > w) {
			return true;
		}
		if(head.getY() > h) {
			return true;
		}
		for(int i=3; i<polyPoints.size(); i++) {
			//check if the head has crashed into the chain
			if(polyPoints.get(i).getX() == head.getX()
					&& polyPoints.get(i).getY() == head.getY()) {
				return true;
			}
		}
		return false;
	}
	
	public void draw(Graphics2D g2d) {
		
		for(int i=polyPoints.size()-1; i>=0; i--) { //draw amino acids
			Amino a = polyPoints.get(i);
			
			if(i == 0) { 
				//update x and y directions for first amino acid from head
				a.setX(head.getX());
				a.setY(head.getY());
				a.setXDir(head.getXDir());
				a.setYDir(head.getYDir());
			} else { 
				//update x and y for all subsequent amino acids
				Amino last = polyPoints.get(i-1);
				a.setX(last.getX());
				a.setY(last.getY());
				a.setXDir(last.getXDir());
				a.setYDir(last.getYDir());
			}
			a.draw(g2d);
		}
		head.step();
		g2d.setPaint(head.getColor());
		g2d.drawOval(head.getX(), head.getY(), SIZE, SIZE); //draw head
	}
}