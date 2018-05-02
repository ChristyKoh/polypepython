package polypepython1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Amino extends Point {
	private Image image;
	private String name;
	private boolean showImage = false;
	
	public Amino(String n) {
		super();
		name = n;
	}
	public Amino(String n, Color c) {
		super();
		name = n;
		color = c;
	}
	public Amino(String n, Color c, Image i) {
		super();
		name = n;
		color = c;
		image = i;
		showImage = true;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public int getWidth() {
		if(image != null) {
			return image.getWidth(null);
		}
		else return SIZE;
	}
	
	public int getHeight() {
		if(image != null) {
			return image.getHeight(null);
		}
		else return SIZE;
	}
	
	public void hideImage() {
		showImage = false;
	}
	
	public void showImage() {
		showImage = true;
	}
	
	public void drawImage(Graphics2D g2d) {
		//System.out.println(image);
		if(image != null && showImage) {
			//System.out.println("drawing image at "+x +" " +y);
			g2d.drawImage(image, x, y, null);
			//g2d.drawImage(image, x, y, x+15, y+30, 0, 0,  image.getWidth(null), image.getHeight(null), null);
		}
		else {
			//System.out.println("drawing circle for " + this.name + " at " + this.getX() + " " + this.getY());
			this.draw(g2d);
		}
	}
	
	public boolean equals(Amino a){
		return(this.getName().equals(a.getName()));
	}
	public boolean equals(String s) {
		return(this.getName().equals(s));
	}
}
