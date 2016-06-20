package view;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




//import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.JRadioButton;
import javax.swing.JTextField;

import main.Main;
import model.Som;


public class TelaDoMenu extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image imagemIcone;
	
	private Som audio;
	
	private JLabel fPlayerL/*, sPlayerL*/;
	private JTextField fPlayerT/*, sPlayerT*/;
	private JButton jogarButton,sairButton,creditosButton,optionButton;
	private JLabel imagemLabel;
	//	private JRadioButton umJogador;
	//	private JRadioButton doisJogador;
	//	private ButtonGroup grupo1;
	public TelaDoMenu(){
		audio = new Som();
		audio.menuIniciar();
		ImageIcon referencia = new ImageIcon("res/SnakeIcon.png");
		imagemIcone = referencia.getImage();

		ImageIcon imagem = new ImageIcon("res/Menu.png");
		imagemLabel = new JLabel(imagem);
		add(imagemLabel);
		
		fPlayerL = new JLabel(new ImageIcon("res/PlayerLabel.png"));
		imagemLabel.add(fPlayerL);


		fPlayerT = new JTextField(10);
		imagemLabel.add(fPlayerT);
		

		//		sPlayerL = new JLabel("Player 2:");
		//		add(sPlayerL);
		//
		//		sPlayerT = new JTextField(20);
		//		add(sPlayerT);
		//
		//		umJogador = new JRadioButton("1 Player",true);
		//		add(umJogador);
		//
		//		doisJogador = new JRadioButton("2 Player",false);
		//		add(doisJogador);
		//
		//		grupo1 = new ButtonGroup();
		//		grupo1.add(umJogador);
		//		grupo1.add(doisJogador);
		//
		jogarButton = new JButton();
		jogarButton.setIcon(new ImageIcon("res/StartButton.png"));
		imagemLabel.add(jogarButton);
		
		creditosButton = new JButton();
		creditosButton.setIcon(new ImageIcon("res/CreditosBar.png"));
		imagemLabel.add(creditosButton);
		
		sairButton = new JButton();
		sairButton.setIcon(new ImageIcon("res/QuitBar.png"));
		imagemLabel.add(sairButton);
		
		optionButton =new JButton();
		optionButton.setIcon(new ImageIcon("res/OpcoesButton.png"));
		imagemLabel.add(optionButton);
		//
		//		if(umJogador.isSelected()==true){
		//			sPlayerL.setVisible(false);
		//			fPlayerT.setVisible(false);
		//		}
		//		if(doisJogador.isSelected()==true){
		//			sPlayerL.setVisible(true);
		//			fPlayerT.setVisible(false);}


		jogarButton.addActionListener(this);
		creditosButton.addActionListener(this);
		sairButton.addActionListener(this);
		optionButton.addActionListener(this);
		
		imagemLabel.setBounds(1,1,1,1);
		
		fPlayerL.setBounds(550, 510, 100, 30);
		fPlayerT.setBounds(650, 510, 130, 30);
		
		jogarButton.setBounds(640,360 , 132, 40);
		jogarButton.setContentAreaFilled(false);
		jogarButton.setBorder(null);
		
		creditosButton.setBounds(641, 400, 132, 40);
		creditosButton.setContentAreaFilled(false);
		creditosButton.setBorder(null);
		
		sairButton.setBounds(641, 440, 132, 40);
		sairButton.setContentAreaFilled(false);
		sairButton.setBorder(null);
		
		optionButton.setBounds(700,10,43,38);
		optionButton.setContentAreaFilled(false);
		optionButton.setBorder(null);
		
		setUndecorated(true);
		setTitle("Smart Snake");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(imagemIcone);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	

	public void actionPerformed(ActionEvent e) {
		if((e.getSource()==jogarButton)){
			audio.botaoInicius();
			if (fPlayerT.getText().trim().equals("")) {  
				JOptionPane.showMessageDialog(null, "Digite um nome");
				} else {  
				audio.menuParar();
				Main.player.setNome(fPlayerT.getText());
				setVisible(false);
				new TelaJogo();
				}  
		}
		
		if((e.getSource()==creditosButton)){
			audio.botaoInicius();
			new Creditos();
		
		}
		if((e.getSource()==sairButton)){
			audio.botaoInicius();
			System.exit(0);
		
		}
		if((e.getSource()==optionButton)){
			audio.botaoInicius();
			new TelaOpcoes();
		
		}
		
	}

}
