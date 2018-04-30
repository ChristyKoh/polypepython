package Random;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

class Surface extends JPanel implements ActionListener {
	
	private final int DELAY = 150;
	private Timer timer;
	
	public Surface() {
		initTimer();
	}
	
	private void initTimer() {
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public Timer getTimer() {
		return this.timer;
	}
	
	private void doDrawing(Graphics2D g2d) {
		
		g2d.setPaint(Color.blue);
		
		int w = getWidth();
		int h = getHeight();
		
		Random r = new Random();
		
		for (int i=0; i < 2000; i++) {
			int x = Math.abs(r.nextInt()) % w;
			int y = Math.abs(r.nextInt()) % h;
			g2d.drawLine(x, y, x, y);
		}
	}

    @Override
    public void paintComponent(Graphics g) {
    	Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        doDrawing(g2d);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	repaint();
    }
}
