package polypepython;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends Applet implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Graphics gfx;
	Image img;
	Thread thread;
	Polypeptide polly;
	
	public void init(){
		this.resize(400, 400);
		img = createImage(400, 400);
		gfx = img.getGraphics();
		polly = new Polypeptide();
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 400, 400);
			polly.draw(gfx);
			
			g.drawImage(img, 0, 0, null);
	}
	
	public void update(Graphics g) {
		paint(g);
		
	}
	public void repaint(Graphics g){
		paint(g);
	}

	@Override
	public void run() {
		for(;;){
			
			this.repaint();
			
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP){
			System.out.println("going up");
			
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN){
			System.out.println("going down");
			
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT){
			System.out.println("going left");
			
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){
			System.out.println("going right");
	
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
