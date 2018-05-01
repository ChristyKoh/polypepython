package polypepython1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Amino extends Point {
	private Image image;
	private String name;
	
	public Amino(String n) {
		super();
		this.name = n;
	}
	public Amino(String n, Color c) {
		super();
		this.name = n;
		this.color = c;
	}
	public Amino(String n, Color c, Image i) {
		super();
		this.name = n;
		this.color = c;
		this.image = i;
		System.out.println( n + " has an image.");
	}
	
	public String getName() {
		return this.name;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public void drawImage(Graphics2D g2d) {
		//System.out.println(image);
		if(image != null) {
			System.out.println("drawing image at "+x +" " +y);
			g2d.drawImage(image, x, y, null);
			//g2d.drawImage(image, x, y, x+15, y+30, 0, 0,  image.getWidth(null), image.getHeight(null), null);
		}
		else {
			//System.out.println("drawing circle");
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
