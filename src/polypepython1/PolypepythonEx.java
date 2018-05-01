package polypepython1;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

public class PolypepythonEx extends JFrame{
	
	public PolypepythonEx() {
		initUI();
	}
	
	private void initUI() {
		final Surface s = new Surface();
		add(s);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Timer timer = s.getTimer();
				timer.stop();
			}
		});
		
		setTitle("Polypepython!");
		setSize(1000,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				PolypepythonEx ex = new PolypepythonEx();
				ex.setVisible(true);
			}
		});
	}
	
}
