package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Som;

public class Creditos extends JFrame implements ActionListener{
		
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image imagemIcone;
	private ImageIcon referencia,a;
	private JLabel label;
	private JButton voltar;
	
	private Som audio;
	
	public Creditos(){
			audio = new Som();
		
			referencia = new ImageIcon("res/SnakeIcon.png");
			imagemIcone = referencia.getImage();
			
			a= new ImageIcon("res/eblema.png");
			
			repaint();
			
			add(label = new JLabel());
			voltar = new JButton();
			label.add(voltar);
				voltar.addActionListener(this);
			
			voltar.setBounds(640, 430, 144, 40);
			voltar.setIcon(new ImageIcon("res/QuitBar.png"));
			voltar.setBorder(null);
			voltar.setContentAreaFilled(false);
			
			setUndecorated(true);
			setTitle("Smart Snake");
			setSize(800,500);
			setIconImage(imagemIcone);
			setVisible(true);
			setLocationRelativeTo(null);
			setResizable(false);
			
	}
	
	
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 500);
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.drawImage(a.getImage(), 10, 15, this);
		g.setColor(Color.BLACK);
		g.drawString("UNIVERSIDADE FEDERAL RURAL DE PERNAMBUCO", 150, 50);
		g.drawString("CURSO: Bacharelado em Sistemas da Informação ", 150, 100);
		g.drawString("ALUNO: Vinícius de Oliveira Andrade", 150, 150);
		g.drawString("PROFESSOR:Dr. Richarlyso N Alves D'Emer", 150, 200);
		g.drawString("DISCIPLINA: Modelagem e Programação Orientada a Objetos", 150, 250);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		audio.botaoInicius();
		dispose();
		
	}
}
