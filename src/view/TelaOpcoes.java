package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import main.Main;
import model.Som;

public class TelaOpcoes extends JFrame implements ActionListener {
	
	
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private Image imagemIcone;
		
		private ImageIcon referencia;
		
		private JRadioButton velocidadeNormal,velocidadeBaixa,VelocidadeRapida;
		private JRadioButton soma,subtracao,multiplicacao,divisao,tudo;
		
		private JLabel label,perguntas;
		private JLabel velocidade,veloNormal,veloBaixa,veloRapida;
		private JLabel operacao,som,sub,mul,di,tu;
		
		private JTextField quantidade;
		
		private JButton voltar;
		
		private ButtonGroup grupoA,grupoB;
		
		private Som audio;
		public TelaOpcoes(){
				
			
				referencia = new ImageIcon("res/SnakeIcon.png");
				imagemIcone = referencia.getImage();
				add(label = new JLabel(new ImageIcon("res/FaseFundo.png")));
				
				/**
				 * =======================Inicialização==========================
				 * */
				perguntas = new JLabel(new ImageIcon("res/Perguntas.png"));
				quantidade = new JTextField(10);
				
				velocidade = new JLabel(new ImageIcon("res/Velocidade.png"));
				veloBaixa = new JLabel(new ImageIcon("res/Devagar.png"));
				veloNormal = new JLabel(new ImageIcon("res/Normal.png"));
				veloRapida = new JLabel(new ImageIcon("res/Rapido.png"));
				
				operacao = new JLabel(new ImageIcon("res/Operacoes.png"));
				som = new JLabel(new ImageIcon("res/Soma.png"));
				sub = new JLabel(new ImageIcon("res/Subtração.png"));
				mul = new JLabel(new ImageIcon("res/Multiplicaçao.png"));
				di = new JLabel(new ImageIcon("res/Divisão.png"));
				tu = new JLabel(new ImageIcon("res/Todas.png"));
				
				soma = new JRadioButton();
				subtracao= new JRadioButton();
				multiplicacao = new JRadioButton();
				divisao = new JRadioButton();
				tudo = new JRadioButton();
				tudo.setSelected(true);
				velocidadeBaixa = new JRadioButton();
				velocidadeNormal = new JRadioButton();
				VelocidadeRapida = new JRadioButton();
				velocidadeNormal.setSelected(true);

				voltar = new JButton();
				
				audio = new Som();
				
				grupoA = new ButtonGroup();
				grupoB = new ButtonGroup();
				
				/**
				 * 
				 * =============================Adicionando ao Label=====================
				 * 
				 * */
				label.add(velocidade);
				label.add(veloBaixa);
				label.add(veloNormal);
				label.add(veloRapida);
				
				label.add(operacao);
				label.add(som);
				label.add(sub);
				label.add(mul);
				label.add(di);
				label.add(tu);
				
				label.add(soma);
				label.add(subtracao);
				label.add(multiplicacao);
				label.add(divisao);
				label.add(tudo);
				
				label.add(velocidadeBaixa);
				label.add(velocidadeNormal);
				label.add(VelocidadeRapida);
				
				label.add(perguntas);
				label.add(quantidade);
				
				label.add(voltar);
				/**
				 * =================Setando local, retirando bordas, deichando transparente, adicionando a grupos=========
				 * 
				 * */
				
				operacao.setBounds(20, 20, 100, 30);
				som.setBounds(30, 50, 100, 30);
				sub.setBounds(30, 80, 100, 30);
				mul.setBounds(34, 110, 110, 33);
				di.setBounds(29, 140, 100, 30);
				tu.setBounds(34, 170, 100, 30);
				
				velocidade.setBounds(20, 210, 100, 30);
				veloBaixa.setBounds(30, 240,100, 30);
				veloNormal.setBounds(34, 270, 100,30);
				veloRapida.setBounds(30, 300, 100, 30);
				
				soma.setBounds(160, 60, 20, 20);
				soma.setBorder(null);
				soma.setContentAreaFilled(false);
				
				subtracao.setBounds(160, 90, 20, 20);
				subtracao.setBorder(null);
				subtracao.setContentAreaFilled(false);
				
				multiplicacao.setBounds(160, 120, 20,20);
				multiplicacao.setBorder(null);
				multiplicacao.setContentAreaFilled(false);
				
				divisao.setBounds(160, 150, 20, 20);
				divisao.setBorder(null);
				divisao.setContentAreaFilled(false);
				
				tudo.setBounds(160, 180, 20, 20);
				tudo.setBorder(null);
				tudo.setContentAreaFilled(false);
				
				grupoA.add(soma);
				grupoA.add(subtracao);
				grupoA.add(multiplicacao);
				grupoA.add(divisao);
				grupoA.add(tudo);
				
				velocidadeBaixa.setBounds(160, 250, 20, 20);
				velocidadeBaixa.setBorder(null);
				velocidadeBaixa.setContentAreaFilled(false);
				
				velocidadeNormal.setBounds(160, 280, 20, 20);
				velocidadeNormal.setBorder(null);
				velocidadeNormal.setContentAreaFilled(false);
				
				VelocidadeRapida.setBounds(160, 310, 20, 20);
				VelocidadeRapida.setBorder(null);
				VelocidadeRapida.setContentAreaFilled(false);
				
				grupoB.add(velocidadeBaixa);
				grupoB.add(velocidadeNormal);
				grupoB.add(VelocidadeRapida);
				
				perguntas.setBounds(300, 30, 122, 30);
				quantidade.setBounds(422,34,30,20);
				quantidade.setText("20");
				
				voltar.addActionListener(this);
				voltar.setBounds(490, 555, 144, 40);
				voltar.setIcon(new ImageIcon("res/QuitBar.png"));
				voltar.setBorder(null);
				voltar.setContentAreaFilled(false);
				
				/**
				 * 
				 * =========================Contruindo Tela=========================================
				 * 
				 * */
			
				setUndecorated(true);
				setTitle("Smart Snake");
				setSize(640,600);
				setIconImage(imagemIcone);
				setVisible(true);
				setLocationRelativeTo(null);
				setResizable(false);
		
		}
		
		
		public JRadioButton getVelocidadeNormal() {
			return velocidadeNormal;
		}

		public JRadioButton getVelocidadeBaixa() {
			return velocidadeBaixa;
		}

		public JRadioButton getVelocidadeRapida() {
			return VelocidadeRapida;
		}
		
		public JRadioButton getSoma() {
			return soma;
		}

		public JRadioButton getSubtracao() {
			return subtracao;
		}

		public JRadioButton getMultiplicacao() {
			return multiplicacao;
		}

		public JRadioButton getDivisao() {
			return divisao;
		}

		public JRadioButton getTudo() {
			return tudo;
		}
		
		public JTextField getQuantidade() {
			return quantidade;
		}
		
		public void actionPerformed(ActionEvent e) {
			audio.botaoInicius();
			Main.opcoes.receberConfiguracoes(this);
			dispose();
		}

	}


