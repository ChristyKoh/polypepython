package polypepython1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Amino extends Point {
	private Image image = null;
	private String name;
	private final int SIZE = 4;
	
	public Amino(String n) {
		super();
		this.name = n;
	}
	public Amino(String n, int x, int y, Color c) {
		super(x,y,c);
		this.name = n;
		this.color = c;
	}
	public Amino(String n, int x, int y, Image i) {
		super(x,y);
		this.name = n;
		this.image = i;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color); //set to color of amino acid
		if(image != null) {
			//TODO draw image
		}
		else {
			g2d.drawOval(x, y, SIZE, SIZE);
		}
	}
	
	public boolean equals(Amino a){
		return(this.getName().equals(a.getName()));
	}
	public boolean equals(String s) {
		return(this.getName().equals(s));
	}
}
