package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TelaJogo extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image imagemIcone;
	
	public TelaJogo(){
		ImageIcon referencia = new ImageIcon("res/SnakeIcon.png");
		imagemIcone = referencia.getImage();
		
		//add(new MenuFase());
		add(new Fase());
		
		
		setTitle("Smart Snake");
		setSize(900,608);
		setIconImage(imagemIcone);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	

}
