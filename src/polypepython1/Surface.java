package polypepython1;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;



class Surface extends JPanel implements ActionListener {
	
	//amino acid instantiation
	private Amino alanine = new Amino("alanine");
	private Amino arginine = new Amino("arginine");
	private Amino asparagine = new Amino("asparagine");
	private Amino aspartic = new Amino("aspartic acid");
	private Amino cysteine = new Amino("cysteine");
	private Amino glutamic = new Amino("glutamic acid");
	private Amino glutamine = new Amino("glutamine");
	private Amino glycine = new Amino("glycine");
	private Amino histidine = new Amino("histidine");
	private Amino isoleucine = new Amino("isoleucine");
	private Amino leucine = new Amino("leucine");
	private Amino lysine = new Amino("lysine");
	private Amino methionine = new Amino("methionine");
	private Amino phenylalanine = new Amino("phenylalanine");
	private Amino proline = new Amino("proline");
	private Amino selenocysteine = new Amino("selenocysteine");
	private Amino serine = new Amino("serine");
	private Amino threonine = new Amino("threonine");
	private Amino tryptophan = new Amino("tryptophan");
	private Amino tyrosine = new Amino("tyrosine");
	private Amino valine = new Amino("valine");
	
	private ArrayList<Amino> aminoList = new ArrayList<Amino>() {{
		add(alanine);
		add(arginine);
		add(asparagine);
		add(aspartic);
		add(cysteine);
		add(glutamic);
		add(glutamine);
		add(glycine);
		add(histidine);
		add(isoleucine);
		add(leucine);
		add(lysine);
		add(methionine);
		add(phenylalanine);
		add(proline);
		add(selenocysteine);
		add(serine);
		add(threonine);
		add(tryptophan);
		add(tyrosine);
		add(valine);
	}};
	
	//TODO add polypeptide keys
		
	private boolean inGame = true;
	private final static int DELAY = 150, WIDTH = 1000, HEIGHT = 500;
	private Timer timer;
	private Polypeptide polly; //polypeptide snake
	private ArrayList<Amino> key; //key to polypeptide sequence
	private int currAmino; //target amino acid
	
	
	public Surface() { //constructor for surface
		initBoard();
	}
	
	private void initBoard() {
		addKeyListener(new TAdapter());
		setFocusable(true);
        setBackground(Color.white);
        setDoubleBuffered(true);
        
        //instantiate variables
        polly = new Polypeptide();
        key = aminoList; //replace with desired protein list 
        currAmino = 0;
        
        //initialize timer
        timer = new Timer(DELAY, this);
		timer.start();
		
		//randomize amino acid positions
		Random r = new Random();	
		for(Amino a: key) {
			a.setX(r.nextInt(WIDTH));
			a.setY(r.nextInt(HEIGHT));
			System.out.println(a.getName() + " is located at " + a.getX() + " " + a.getY());
		}
	}
	
	public Timer getTimer() {
		return this.timer;
	}

	private void doDrawing(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		if (inGame){
			g2d.setPaint(Color.blue);
			polly.draw(g2d);
	
			//draw 5 next amino acids
			for(int k=0; k<5; k++) {
				//TODO check if random amino position is on polypeptide
				key.get(currAmino + k).draw(g2d);
			}
		} else {
			gameOver(g2d);
		}
		
	}
	
private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2, HEIGHT / 2);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	repaint();
    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            
            if (key == KeyEvent.VK_UP && polly.getYDir() != 1){
    			polly.setXDir(0);
    			polly.setYDir(-1);
    			System.out.println("going up!");
    		}
    		if (key == KeyEvent.VK_DOWN && polly.getYDir() != -1){
    			polly.setXDir(0);
    			polly.setYDir(1);
    			System.out.println("going down!");
    		}
    		if (key == KeyEvent.VK_LEFT && polly.getXDir() != 1){
    			polly.setXDir(-1);
    			polly.setYDir(0);
    			System.out.println("going left!");
    		}
    		if (key == KeyEvent.VK_RIGHT && polly.getXDir() != -1){
    			polly.setXDir(1);
    			polly.setYDir(0);
    			System.out.println("going right!");
    		}
    		
        }
    }
}
