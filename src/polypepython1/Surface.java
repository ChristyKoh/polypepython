package polypepython1;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



class Surface extends JPanel implements ActionListener {
	
	private boolean inGame = true;
	public final static int DELAY = 100, PADDING = 50, 
			WIDTH = 500, HEIGHT = 400, SIZE = 8,
			PROMPTHEIGHT = 50;
	private Timer timer;
	private Polypeptide polly; //polypeptide snake
	private ArrayList<Amino> key; //key to polypeptide sequence
	private int currAmino; //target amino acid
	
	//color instantiation
	Color base = new Color(69, 117, 186);
	Color acid = new Color(208, 51, 147);
	Color pol = new Color(121, 64, 152);
	Color npol = new Color(37, 139, 68);
	Color rf1 = new Color(221, 28, 36);
	
	//amino acid image instantiation
	private Image alaImg, argImg, asnImg, aspImg,
	cysImg, gluImg, glnImg, glyImg, hisImg, ileImg, 
	leuImg, lysImg, metImg, pheImg, proImg, secImg,
	serImg, thrImg, trpImg, tyrImg, valImg;
	
	//amino acid instantiation
	private Amino ala, arg, asn, asp, cys, glu, 
	gln, gly, his, ile, leu, lys, met, phe, pro, 
	sec, ser, thr, trp, tyr, val;
	
	private ArrayList<Amino> aminoList = new ArrayList<Amino>() {{
		add(ala);
		add(arg);
		add(asn);
		add(asp);
		add(cys);
		add(glu);
		add(gln);
		add(gly);
		add(his);
		add(ile);
		add(leu);
		add(lys);
		add(met);
		add(phe);
		add(pro);
		add(sec);
		add(ser);
		add(thr);
		add(trp);
		add(tyr);
		add(val);
	}};
	
	private ArrayList<Amino> insulin;
	//TODO add more polypeptide keys
	
	public Surface() { //constructor for surface
		initBoard();
	}
	
	private void initBoard() {
		addKeyListener(new TAdapter());
		setFocusable(true);
        setBackground(Color.white);
        setDoubleBuffered(true);
        loadImages();
        initAminos();
        initKeys();
        
        //instantiate variables
        polly = new Polypeptide();
        //key = aminoList; //replace with desired protein list 
        currAmino = 0;
        
        //initialize timer
        timer = new Timer(DELAY, this);
		timer.start();
		
		//randomize amino acid positions
		Random r = new Random();	
		for(Amino a: key) {
			a.setX(r.nextInt((WIDTH-2*PADDING)/SIZE)*SIZE + PADDING); //make sure coordinates are a multiple of SIZE
			a.setY(r.nextInt((HEIGHT-2*PADDING)/SIZE)*SIZE + PADDING);
			//System.out.println(a.getName() + " is located at " + a.getX() + " " + a.getY());
		}
	}
	
	public Timer getTimer() {
		return this.timer;
	}

	private void doDrawing(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		if (inGame){
			
			//draw boundaries
			g2d.setPaint(Color.black);
			g2d.drawRect(0, 0, WIDTH, HEIGHT+10); //draw boundaries
			
			//set up amino acid prompter
			g2d.setPaint(Color.gray);
			g2d.fillRect(0, HEIGHT+10, WIDTH, PROMPTHEIGHT);
			g2d.setPaint(Color.white);
			g2d.drawString("Next amino acid: " + key.get(currAmino).getName(), PADDING, HEIGHT+PROMPTHEIGHT/2);
			
			//draw polypeptide
			g2d.setPaint(Color.blue);
			polly.draw(g2d);
	
			//draw 5 next amino acids
			for(int k=0; k<5; k++) {
				
				//TODO check if random amino position is on polypeptide
				
				//make sure no OutOfBoundsException happens
				if(k+currAmino<key.size()) 
					//key.get(currAmino + k).drawImage(g2d);
					key.get(currAmino + k).draw(g2d);

			}
			
		} else {
			gameOver(g2d);
		}
		
	}
	
	
	private void loadImages() {

	    ImageIcon iid = new ImageIcon("src/images/Nonpolar/Glycine.png");
	    glyImg = iid.getImage();
	    
	    //TODO add the rest of the images
	    
	}
	
	private void initAminos() {
		ala = new Amino("alanine", npol);
		arg = new Amino("arginine", base);
		asn = new Amino("asparagine", pol);
		asp = new Amino("aspartic acid", acid);
		cys = new Amino("cysteine", pol);
		glu = new Amino("glutamic acid", acid);
		gln = new Amino("glutamine", pol);
		gly = new Amino("glycine", npol, glyImg);
		his = new Amino("histidine", base);
		ile = new Amino("isoleucine", npol);
		leu = new Amino("leucine", npol);
		lys = new Amino("lysine", base);
		met = new Amino("methionine", npol);
		phe = new Amino("phenylalanine", npol);
		pro = new Amino("proline", npol);
		sec = new Amino("selenocysteine", acid); //double check this
		ser = new Amino("serine", pol);
		thr = new Amino("threonine", pol);
		trp = new Amino("tryptophan", npol);
		tyr = new Amino("tyrosine", pol);
		val = new Amino("valine", npol);
	}
	
	private void initKeys() {
		insulin = new ArrayList<Amino>() {{
			add(gly);
			add(ile);
			add(val);
			add(glu);
			add(gln);
			add(cys);
			add(cys);
			add(thr);
			add(ser);
			add(ile);
			add(cys);
			add(ser);
			add(leu);
			add(tyr);
			add(gln);
			add(leu);
			add(glu);
			add(asn);
			add(tyr);
		}};
		
		key = insulin;
	}
	
	/*
	 * Checks to see if polypeptide is touching an amino acid. This method will
	 * lengthen the polypeptide and make the consumed amino disappear if they
	 * are indeed touching.
	 */
	private void checkAmino() {
		//if the head is in the same position as an amino acid
		if(polly.getX() == key.get(currAmino).getX() &&
				polly.getY() == key.get(currAmino).getY()) {
			
			//append current Amino object onto the polypeptide
			polly.add(key.get(currAmino));
			currAmino++;
			
			//if all amino acids have been collected, end game
			if (currAmino == key.size()) inGame = false; 
		}
		
	}
	
	/*
	 * Checks to see if polypeptide has collided with itself or the boundaries.
	 * This method will set inGame to false if a collision has occurred, ending
	 * the game.
	 */
	private void checkCollision() {

        if (polly.hasCollision(WIDTH, HEIGHT)) inGame = false;
        if (!inGame) timer.stop();
		
	}
	
	private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.black);
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
    	checkCollision();
    	checkAmino();
    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            
            if (key == KeyEvent.VK_UP && polly.getYDir() != 1){
    			polly.setXDir(0);
    			polly.setYDir(-1);
    			//System.out.println("going up!");
    		}
    		if (key == KeyEvent.VK_DOWN && polly.getYDir() != -1){
    			polly.setXDir(0);
    			polly.setYDir(1);
    			//System.out.println("going down!");
    		}
    		if (key == KeyEvent.VK_LEFT && polly.getXDir() != 1){
    			polly.setXDir(-1);
    			polly.setYDir(0);
    			//System.out.println("going left!");
    		}
    		if (key == KeyEvent.VK_RIGHT && polly.getXDir() != -1){
    			polly.setXDir(1);
    			polly.setYDir(0);
    			//System.out.println("going right!");
    		}
    		
        }
    }
}
