package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TelaJogo extends JFrame{
	private static final long serialVersionUID = 1L;
	public TelaJogo(){
		add(new Fase());
		
		setTitle("Smart Snake");
		setSize(900,608);
		setIconImage(new ImageIcon("res/SnakeIcon.png").getImage());
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
