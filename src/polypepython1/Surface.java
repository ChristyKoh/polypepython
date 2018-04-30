package polypepython1;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;



class Surface extends JPanel implements ActionListener, KeyListener {
	
	
	
	//amino acid instantiation
	//TODO add all amino acids
	private Amino valine = new Amino("valine");
	private Amino methionine = new Amino("methionine");
	private Amino glycine = new Amino("glycine");
	private Amino lysine = new Amino("lysine");
	private Amino leucine = new Amino("leucine");
	private Amino serine = new Amino("serine");
	private Amino asparagine = new Amino("asparagine");
	private Amino arginine = new Amino("arginine");
	
	//polypeptide keys
	public ArrayList<String> insulin = new ArrayList<String>(10);
		
	private final int DELAY = 150;
	private Timer timer;
	private Polypeptide polly = new Polypeptide();
	private ArrayList<String> key = insulin;
	private ArrayList<Amino> pollyList = polly.get();
	private int currAmino = 0; //target amino acid
	
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
	
	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setPaint(Color.blue);
		
		int w = getWidth();
		int h = getHeight();
		
		Random r = new Random();
		polly.draw(g2d);
		
		//draw 5 random amino acids
		for(int k=0; k<5; k++) {
			//TODO pick random amino and draw
		}
		/*//draw polypeptide
		for(int i=0; i<pollyList.size(); i++) {
			Amino p = pollyList.get(i);
			g.drawOval(p.getX(), p.getY(), 4, 4);
			p.setX(p.getX()+p.getXDir()*4);
			p.setY(p.getY()+p.getYDir()*4);
			if(i>0) { //only update directions for body segments
				p.setXDir(pollyList.get(i-1).getXDir());
				p.setYDir(pollyList.get(i-1).getYDir());
			}
			//System.out.println("x: "+p.getXDir() + " | y: " + p.getYDir());
		}*/
	}

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	repaint();
    }
    
    
    //TODO get key stuff working
    @Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP && pollyList.get(0).getYDir() != 1){
			pollyList.get(0).setXDir(0);
			pollyList.get(0).setYDir(-1);
			System.out.println("going up!");
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && pollyList.get(0).getYDir() != -1){
			pollyList.get(0).setXDir(0);
			pollyList.get(0).setYDir(1);
			System.out.println("going down!");
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && pollyList.get(0).getXDir() != 1){
			pollyList.get(0).setXDir(-1);
			pollyList.get(0).setYDir(0);
			System.out.println("going left!");
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && pollyList.get(0).getXDir() != -1){
			pollyList.get(0).setXDir(1);
			pollyList.get(0).setYDir(0);
			System.out.println("going right!");
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
