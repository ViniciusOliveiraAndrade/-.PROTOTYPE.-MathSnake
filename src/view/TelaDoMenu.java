package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import main.Main;
import model.Som;


public class TelaDoMenu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private Som audio;

	private JLabel primeiroPlayerLabel, segundoPlayerLabel;
	private JTextField primeiroPlayerField, segundoPlayerLabeField;
	private JButton jogarButton,sairButton,creditosButton,optionButton;
	private JLabel imagemLabel;
	private JRadioButton doisJogador;
	public TelaDoMenu(){
		audio = new Som();
		audio.menuIniciar();

		ImageIcon imagem = new ImageIcon("res/Menu.png");
		imagemLabel = new JLabel(imagem);
		add(imagemLabel);

		primeiroPlayerLabel = new JLabel(new ImageIcon("res/PlayerLabel.png"));
		imagemLabel.add(primeiroPlayerLabel);


		primeiroPlayerField = new JTextField(10);
		imagemLabel.add(primeiroPlayerField);


		segundoPlayerLabel = new JLabel(new ImageIcon("res/PlayerLabel.png"));
		imagemLabel.add(segundoPlayerLabel);

		segundoPlayerLabeField = new JTextField(20);
		imagemLabel.add(segundoPlayerLabeField);

		doisJogador = new JRadioButton("MultPlayer",false);
		doisJogador.setBorder(null);
		doisJogador.setContentAreaFilled(false);
		doisJogador.setFont(new Font(null, Font.BOLD, 15));
		doisJogador.setForeground(Color.WHITE);
		doisJogador.addActionListener(this);
		imagemLabel.add(doisJogador);

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

		jogarButton.addActionListener(this);
		creditosButton.addActionListener(this);
		sairButton.addActionListener(this);
		optionButton.addActionListener(this);

		imagemLabel.setBounds(1,1,1,1);

		primeiroPlayerLabel.setBounds(550, 510, 100, 30);
		primeiroPlayerField.setBounds(650, 510, 130, 30);

		segundoPlayerLabel.setBounds(550, 550, 100, 30);
		segundoPlayerLabeField.setBounds(650, 550, 130, 30);
		segundoPlayerLabeField.setVisible(false);
		segundoPlayerLabel.setVisible(false);

		doisJogador.setBounds(440, 550, 100, 30);

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
		setIconImage(new ImageIcon("res/SnakeIcon.png").getImage());
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);

	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==doisJogador){
			if(doisJogador.isSelected()){
				segundoPlayerLabeField.setVisible(true);
				segundoPlayerLabel.setVisible(true);
			}else{segundoPlayerLabeField.setVisible(false);
			segundoPlayerLabel.setVisible(false);}
		}
		if((e.getSource()==jogarButton)){
			audio.botaoInicius();
			if(doisJogador.isSelected()){
				if ((primeiroPlayerField.getText().trim().equals(""))||(segundoPlayerLabeField.getText().trim().equals(""))) {  
					JOptionPane.showMessageDialog(null, "Digite os dois nomes");
				} else {  
					audio.menuParar();
					Main.player.setNome(primeiroPlayerField.getText());
					Main.opcoes.setMultP(doisJogador.isSelected());
					setVisible(false);

					new TelaJogo();
				}
			}if(!doisJogador.isSelected()){
				if (primeiroPlayerField.getText().trim().equals("")) {  
					JOptionPane.showMessageDialog(null, "Digite um nome");
				} else {  
					audio.menuParar();
					Main.player.setNome(primeiroPlayerField.getText());
					Main.opcoes.setMultP(doisJogador.isSelected());
					setVisible(false);

					new TelaJogo();
				}
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
