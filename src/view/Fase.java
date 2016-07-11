package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;









import control.EscreverXML;
import main.Main;
import model.Maca;
import model.Missao;
import model.PanelMenu;
import model.Ranking;
import model.Snake;
import model.Som;

public class Fase extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int LARGURA = 625;
	private final int ALTURA = 580;
	private final int BOLINHA_TAMANHO = 20;
	private final int TAMANHOMATRIZ = 960;
	private int VELOCIDADE = 140;


	private JButton recomecar = new JButton("Recomeçar");
	private JButton sair = new JButton("Sair");


	private Random gerador = new Random();

	private Som audio;

//	private final int x[] = new int[TAMANHOMATRIZ];
//	private final int y[] = new int[TAMANHOMATRIZ];


	private Image cabeca;
	private Image corpo;
	private Image fundo;

	private int missaoX[] = new int [4];
	private int missaoY[] = new int [4];

	private int score=0;
	private int maissaoX,maissaoY;
	private int tamanhoCobra;
	private int vida=3;
	private int aparencia=0;
	private int quantidadeDePerguntas; 
	private int missaoResultado;

	private boolean esquerda = false;
	private boolean direita = true;
	private boolean cima = false;
	private boolean baixo = false;
	private boolean ativo = true;

	private JFrame j;
	private PanelMenu menuzinho = new PanelMenu();
	private Missao missao;
	private Missao missaoErrada1;
	private Missao missaoErrada2;
	private Missao missaoErrada3;
	private Missao missaoErrada4;
	
	private Maca maca2;
	
	private Color azula = new Color(204);
	
	private Timer timer;
	
	private Ranking ranking;
	
	private EscreverXML escreve;
	Snake cobra;
	public Fase (){
		audio = new Som();
		audio.jogoIniciar();
		
		fundo = new ImageIcon("res/FaseFundo.png").getImage();
		
		setFocusable(true);
		setDoubleBuffered(true);

		addKeyListener(new AdaptadorTeclado());
		
		iniciarGame();
		
		missao = new Missao();
		
		Main.opcoes.carregarConfiguracoes(this, missao);
		
		maca2 = new Maca();
		maca2.start();
		maca2.setX(-100);
		maca2.setY(-100);
		
		missaoErrada1 = new Missao();
		missaoErrada2 = new Missao();
		missaoErrada3 = new Missao();
		missaoErrada4 = new Missao();
		
		ranking = new Ranking();
		escreve = new EscreverXML(ranking);
		ranking.setRanking(escreve.buscarXML());
		
	}
	private void iniciarGame() {
		cobra = new Snake(TAMANHOMATRIZ, 0, 0, "direita");
		
		Main.player.carregarQuantidade();
		gerarMissaoLocal();	
		gerarMissaoErradaLocal();
		timer = new Timer(VELOCIDADE, this);
		timer.start();

	}

	/**
	 * 
	 * ======================================Area de desenho==================================
	 * 
	 * */


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(azula);
		
		g.drawImage(fundo, 0, -5, this);

		missaoTela(missao,g,missaoErrada1,missaoErrada2,missaoErrada3,missaoErrada4);
		
		doDrawing(g);

		g.setColor(Color.CYAN);
		g.fillRect(640, 0, 260, 600);

		menuzinho.menudesenhar(g,score,vida,missao);
		
		sprite();

	}

	private void doDrawing(Graphics g) {
		
		if (ativo) {
			g.drawImage(maca2.getImagem(), maca2.getX(), maca2.getY(), this);
			cobra.desenharCobra(g);
		}     
	}

	private void missaoTela(Missao missao,Graphics g,Missao errada1,Missao errada2,Missao errada3,Missao errada4){
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.BOLD, 20));
		g.drawImage(missao.getImagem(), maissaoX, maissaoY,this);
		g.drawString(""+missao.getResultado(), maissaoX+2, maissaoY+17);
		
		g.drawImage(missao.getImagem(), missaoX[0], missaoY[0],this);
		g.drawString(""+errada1.getResultado(), missaoX[0]+2, missaoY[0]+17);
		
		g.drawImage(missao.getImagem(), missaoX[1], missaoY[1],this);
		g.drawString(""+errada2.getResultado(), missaoX[1]+2, missaoY[1]+17);
		
		g.drawImage(missao.getImagem(), missaoX[2], missaoY[2],this);
		g.drawString(""+errada3.getResultado(), missaoX[2]+2, missaoY[2]+17);
		
		g.drawImage(missao.getImagem(), missaoX[3], missaoY[3],this);
		g.drawString(""+errada4.getResultado(), missaoX[3]+2, missaoY[3]+17);
		

	}

	/**
	 * 
	 * =========================================Area de checar posições===============================================
	 * 
	 */

	private void checarMissaoHit() {

		if ((cobra.getCabeca().x  == maissaoX) && (cobra.getCabeca().y == maissaoY)) {
			missaoResultado=missao.getResultado();
			audio.acertoIniciar();
			
			if(missao.checarMissao(missaoResultado)){
				score+=1;
				cobra.addCorpo();
				Main.player.receberAcertos(missao,missaoResultado);
				gerarMissao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}
		}
		if ((cobra.getCabeca().x  == missaoX[0]) && (cobra.getCabeca().y == missaoY[0])) {
			missaoResultado=missaoErrada1.getResultado();
			audio.acertoIniciar();
			if(missao.checarMissao(missaoResultado)){
				score+=1;
				cobra.addCorpo();
				Main.player.receberAcertos(missao,missaoResultado);
				gerarMissao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}else{
				score--;
				cobra.retiraCorpo();
				vida--;
				Main.player.receberErros(missao,missaoResultado);
				gerarMissao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}
		}
		if ((cobra.getCabeca().x == missaoX[1]) && (cobra.getCabeca().y == missaoY[1])) {
			missaoResultado=missaoErrada2.getResultado();
			audio.acertoIniciar();
			if(missao.checarMissao(missaoResultado)){
				score+=1;
				cobra.addCorpo();
				Main.player.receberAcertos(missao,missaoResultado);
				gerarMissao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}else{
				score--;
				cobra.retiraCorpo();
				vida--;
				Main.player.receberErros(missao,missaoResultado);
				gerarMissao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}
		}
		if ((cobra.getCabeca().x == missaoX[2]) && (cobra.getCabeca().y == missaoY[2])) {
			missaoResultado=missaoErrada3.getResultado();
			audio.acertoIniciar();
			if(missao.checarMissao(missaoResultado)){
				score+=1;
				cobra.addCorpo();
				Main.player.receberAcertos(missao,missaoResultado);
				gerarMissao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}else{
				score--;
				cobra.retiraCorpo();
				vida--;
				Main.player.receberErros(missao,missaoResultado);
				gerarMissao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}
		}
		if ((cobra.getCabeca().x == missaoX[3]) && (cobra.getCabeca().y == missaoY[3])) {
			missaoResultado=missaoErrada4.getResultado();
			audio.acertoIniciar();
			if(missao.checarMissao(missaoResultado)){
				score+=1;
				cobra.addCorpo();
				Main.player.receberAcertos(missao,missaoResultado);
				gerarMissao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}else{
				score--;
				cobra.retiraCorpo();
				vida--;
				Main.player.receberErros(missao,missaoResultado);
				gerarMissao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}
		}
	}

	private void checarColisao() {

		if ((cobra.getCabeca().y >= ALTURA)||(cobra.getCabeca().y < 0)) {
			vida--;
			checarVida();
			cobra.recomecar();
		}

		if ((cobra.getCabeca().x >= LARGURA)||(cobra.getCabeca().x < 0)) {
			vida--;
			checarVida();
			cobra.recomecar();
		}

		if(!ativo) {
			timer.stop();
		}
	}

	public boolean checarMissao(int resultado){

		boolean a = true;
		if(this.missao.getResultado()==resultado){
			a = true;
		}
		if(this.missao.getResultado()!=resultado){
			a = false;
		}
		return a;
	}
	
	public void checarPerguntas(){
		if(quantidadeDePerguntas<=0){
			ativo=false;
			audio.jogoParar();
			recomecar();
		}
	}

	private void checarMaca2() {
		
		if ((cobra.getCabeca().x  == maca2.getX()) && (cobra.getCabeca().y == maca2.getY())) {
			audio.acertoIniciar();
			if(vida<=2){
			vida++;}
			maca2.setAtivo(false);
			maca2.setX(-100);
			maca2.setY(-100);
			
		}
	}
	
	private void checarVida(){
		if(vida<=0){
			ativo=false;
			audio.jogoParar();
			missaoResultado=missao.getResultado();
			maca2.setX(-100);
			maca2.setY(-100);
			recomecar();
		}
	}
	/**
	 * 
	 * =====================================================Area de Atualizão=================================================
	 * 
	 * */
	private void gerarMissao(){
		missao.missao();
		missaoErrada1.missao();
		missaoErrada2.missao();
		missaoErrada3.missao();
		missaoErrada4.missao();
	}

	private void recomecar(){
		ranking.addNoRanking();
		audio.jogoParar();
		j = new JFrame();
		j.setTitle("Mais uma vez?");
		j.setSize(220,65);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setIconImage(new ImageIcon("res/SnakeIcon.png").getImage());
		j.setVisible(true);
		j.setLocationRelativeTo(null);
		j.setResizable(false);

		j.add(recomecar);
		j.add(sair);

		j.setLayout(new FlowLayout());

		recomecar.addActionListener(this);
		sair.addActionListener(this);

	}

	private void sprite(){
		this.aparencia++;
		if(this.aparencia>=5){
			this.aparencia=0;
		}
		menuzinho.setAparencia(this.aparencia);
	}

	private void gerarMissaoLocal() {

		int r = gerador.nextInt(32);
		maissaoX = ((r * BOLINHA_TAMANHO));

		r = gerador.nextInt(32);
		maissaoY = ((r * BOLINHA_TAMANHO));

		if((maissaoX<0)||(maissaoX>=LARGURA)||(maissaoY<0)||(maissaoY>=ALTURA)){
			gerarMissaoLocal();
		}
	}

	private void gerarMissaoErradaLocal() {
		for(int i=0;i<4;i++){
			int r = gerador.nextInt(32);
			missaoX[i] = ((r * BOLINHA_TAMANHO));

			r = gerador.nextInt(32);
			missaoY[i] = ((r * BOLINHA_TAMANHO));
		}
		for(int i=0;i<4;i++){
			if((missaoX[i]<0)||(missaoX[i]>=LARGURA)||(missaoY[i]<0)||(missaoY[i]>=ALTURA)){
				gerarMissaoErradaLocal();
			}
		}
	}
	/**
	 * 
	 * ==========================================Area de eventos====================================
	 * 
	 * */


	private class AdaptadorTeclado extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if ((key == KeyEvent.VK_LEFT) && (!cobra.isDireita())||(key == KeyEvent.VK_A) && (!direita)) {
				
				cobra.lado("esquerda");
			}

			if ((key == KeyEvent.VK_RIGHT) && (!cobra.isEsquerda())||(key == KeyEvent.VK_D) && (!esquerda)) {
				cobra.lado("direita");
			}

			if ((key == KeyEvent.VK_UP) && (!cobra.isBaixo())||(key == KeyEvent.VK_W)&& (!baixo)) {
				cobra.lado("cima");
			}

			if ((key == KeyEvent.VK_DOWN) && (!cobra.isCima())||(key == KeyEvent.VK_S)&& (!cima)) {
				cobra.lado("baixo");
			}
			
			if (key == KeyEvent.VK_P){
				if(ativo){
					ativo=false;
					}else{
						ativo=true;
						}
				
			}
		}
	
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==recomecar){
			
			cobra.criaCorpo();
			
			escreve.gerarXML();
			audio.jogoIniciar();
			score=0;
			ativo=true;
			tamanhoCobra=3;
			quantidadeDePerguntas=Main.opcoes.getQuantidadeDePerguntas();
			Main.player.recomecar();
			j.dispose();
			timer.start();
			vida=3;
			
		}if(e.getSource()==sair){
			ranking.ordenarRanking();
			ranking.morstrarDesempenho();
			audio.botaoInicius();
			System.exit(0);
		}
		
		if (ativo) {
			checarVida();
			if(ativo){
			checarPerguntas();
			checarMissaoHit();
			checarMaca2();
			checarColisao();
			cobra.mover();
			repaint();
			}
		}
	}
		
	public void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}
	
	public void setQuantidadeDePerguntas(int quantidadeDePerguntas) {
		this.quantidadeDePerguntas = quantidadeDePerguntas;
	}
	
}
