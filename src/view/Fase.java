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

	private final int x[] = new int[TAMANHOMATRIZ];
	private final int y[] = new int[TAMANHOMATRIZ];


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
//		tamanhoCobra = 3;
//
//		for (int z = 0; z < tamanhoCobra; z++) {
//			x[z] = 0 - z * 20;
//			y[z] = 0;
//		}
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

//	private void carregarImagens() {
//		if(cima){
//			ImageIcon iid = new ImageIcon("res/cabecaNorte.png");
//			cabeca = iid.getImage();
//		}
//		if(esquerda){
//			ImageIcon iid = new ImageIcon("res/cabecaOeste.png");
//			cabeca = iid.getImage();
//		}
//		if(direita){
//			ImageIcon iid = new ImageIcon("res/cabecaLeste.png");
//			cabeca = iid.getImage();
//		}	
//		if(baixo){
//			ImageIcon iid = new ImageIcon("res/cabecaSul.png");
//			cabeca = iid.getImage();
//		}
//		
//		if(cima){
//			ImageIcon iih = new ImageIcon("res/corpoNorte.png");
//			corpo = iih.getImage();
//		}
//		if(esquerda){
//			ImageIcon iih = new ImageIcon("res/corpoOeste.png");
//			corpo = iih.getImage();
//		}
//		if(direita){
//			ImageIcon iih = new ImageIcon("res/corpoLeste.png");
//			corpo = iih.getImage();
//		}	
//		if(baixo){
//			ImageIcon iih = new ImageIcon("res/corpoSul.png");
//			corpo = iih.getImage();
//		}
//	}

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
//			carregarImagens();
//			for (int z = 0; z < tamanhoCobra; z++) {
//				if (z == 0) {
//					g.drawImage(cabeca, x[z], y[z], this);
//				} else {
//					g.drawImage(corpo, x[z], y[z], this);
//				}
//			}
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

		if ((x[0] == maissaoX) && (y[0] == maissaoY)) {
			missaoResultado=missao.getResultado();
			audio.acertoIniciar();
			
			if(missao.checarMissao(missaoResultado)){
				score+=1;
				tamanhoCobra++;
				Main.player.receberAcerto(missao,missaoResultado);
				Main.player.addAcerto();
				missao.missao();
				missaoErrada1.missao();
				missaoErrada2.missao();
				missaoErrada3.missao();
				missaoErrada4.missao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}
		}
		if ((x[0] == missaoX[0]) && (y[0] == missaoY[0])) {
			missaoResultado=missaoErrada1.getResultado();
			audio.acertoIniciar();
			if(missao.checarMissao(missaoResultado)){
				score+=1;
				tamanhoCobra++;
				Main.player.receberAcerto(missao,missaoResultado);
				Main.player.addAcerto();
				missao.missao();
				missaoErrada1.missao();
				missaoErrada2.missao();
				missaoErrada3.missao();
				missaoErrada4.missao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}else{
				score--;
				tamanhoCobra--;
				vida--;
				Main.player.receberErro(missao,missaoResultado);
				Main.player.addErro();
				missao.missao();
				missaoErrada1.missao();
				missaoErrada2.missao();
				missaoErrada3.missao();
				missaoErrada4.missao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}
		}
		if ((x[0] == missaoX[1]) && (y[0] == missaoY[1])) {
			missaoResultado=missaoErrada2.getResultado();
			audio.acertoIniciar();
			if(missao.checarMissao(missaoResultado)){
				score+=1;
				tamanhoCobra++;
				Main.player.receberAcerto(missao,missaoResultado);
				Main.player.addAcerto();
				missao.missao();
				missaoErrada1.missao();
				missaoErrada2.missao();
				missaoErrada3.missao();
				missaoErrada4.missao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}else{
				score--;
				tamanhoCobra--;
				vida--;
				Main.player.receberErro(missao,missaoResultado);
				Main.player.addErro();
				missao.missao();
				missaoErrada1.missao();
				missaoErrada2.missao();
				missaoErrada3.missao();
				missaoErrada4.missao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}
		}
		if ((x[0] == missaoX[2]) && (y[0] == missaoY[2])) {
			missaoResultado=missaoErrada3.getResultado();
			audio.acertoIniciar();
			if(missao.checarMissao(missaoResultado)){
				score+=1;
				tamanhoCobra++;
				Main.player.receberAcerto(missao,missaoResultado);
				Main.player.addAcerto();
				missao.missao();
				missaoErrada1.missao();
				missaoErrada2.missao();
				missaoErrada3.missao();
				missaoErrada4.missao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}else{
				score--;
				tamanhoCobra--;
				vida--;
				Main.player.receberErro(missao,missaoResultado);
				Main.player.addErro();
				missao.missao();
				missaoErrada1.missao();
				missaoErrada2.missao();
				missaoErrada3.missao();
				missaoErrada4.missao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}
		}
		if ((x[0] == missaoX[3]) && (y[0] == missaoY[3])) {
			missaoResultado=missaoErrada4.getResultado();
			audio.acertoIniciar();
			if(missao.checarMissao(missaoResultado)){
				score+=1;
				tamanhoCobra++;
				Main.player.receberAcerto(missao,missaoResultado);
				Main.player.addAcerto();
				missao.missao();
				missaoErrada1.missao();
				missaoErrada2.missao();
				missaoErrada3.missao();
				missaoErrada4.missao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}else{
				score--;
				tamanhoCobra--;
				vida--;
				Main.player.receberErro(missao,missaoResultado);
				Main.player.addErro();
				missao.missao();
				missaoErrada1.missao();
				missaoErrada2.missao();
				missaoErrada3.missao();
				missaoErrada4.missao();
				quantidadeDePerguntas --;
				gerarMissaoLocal();
				gerarMissaoErradaLocal();
			}
		}
	}

	private void checarColisao() {

//		for (int z = tamanhoCobra; z > 0; z--) {
//
//			if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
//				ativo = false;
//				audio.jogoParar();
//				recomecar();
//			}
//		}

		if ((y[0] >= ALTURA)||(y[0] < 0)) {
			vida--;
			if(vida<=0){
				ativo=false;
				audio.jogoParar();
				recomecar();
			}
			esquerda=false;
			cima=false;
			baixo=false;
			direita=true;
			for (int z = 0; z < tamanhoCobra; z++) {
				x[z] = 0 - z * 20;
				y[z] = 0;

			}

		}

		if ((x[0] >= LARGURA)||(x[0] < 0)) {

			vida--;
			if(vida<=0){
				ativo=false;
				audio.jogoParar();
				recomecar();
			}
			esquerda=false;
			cima=false;
			baixo=false;
			direita=true;
			for (int z = 0; z < tamanhoCobra; z++) {
				x[z] = 0 - z * 20;
				y[z] = 0;
			}
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
		
		if ((x[0] == maca2.getX()) && (y[0] == maca2.getY())) {
			audio.acertoIniciar();
			if(vida<=2){
			vida++;}
			maca2.setAtivo(false);
			maca2.setX(-100);
			maca2.setY(-100);
			
		}
	}
	
	private void checarvida(){
		if(vida==0){
			ativo=false;
			recomecar();
			missaoResultado=missao.getResultado();
			maca2.setX(-100);
			maca2.setY(-100);
		}
	}
	/**
	 * 
	 * =====================================================Area de Atualizão=================================================
	 * 
	 * */


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

//	private void mover() {
//
//		for (int z = tamanhoCobra; z > 0; z--) {
//			x[z] = x[(z - 1)];
//			y[z] = y[(z - 1)];
//		}
//
//		if (esquerda) {
//			x[0] -= BOLINHA_TAMANHO;
//		}
//
//		if (direita) {
//			x[0] += BOLINHA_TAMANHO;
//		}
//
//		if (cima) {
//			y[0] -= BOLINHA_TAMANHO;
//		}
//
//		if (baixo) {
//			y[0] += BOLINHA_TAMANHO;
//		}
//	}

	/**
	 * 
	 * ==========================================Area de eventos====================================
	 * 
	 * */


	private class AdaptadorTeclado extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if ((key == KeyEvent.VK_LEFT) && (!direita)||(key == KeyEvent.VK_A) && (!direita)) {
				esquerda = true;
				cima = false;
				baixo = false;
			}

			if ((key == KeyEvent.VK_RIGHT) && (!esquerda)||(key == KeyEvent.VK_D) && (!esquerda)) {
				direita = true;
				cima = false;
				baixo = false;
			}

			if ((key == KeyEvent.VK_UP) && (!baixo)||(key == KeyEvent.VK_W)&& (!baixo)) {
				cima = true;
				direita = false;
				esquerda = false;
			}

			if ((key == KeyEvent.VK_DOWN) && (!cima)||(key == KeyEvent.VK_S)&& (!cima)) {
				baixo = true;
				direita = false;
				esquerda = false;
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==recomecar){
			for (int z = 0; z < tamanhoCobra; z++) {
				x[z] = 0 - z * 20;
				y[z] = 0;
			}
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
			checarvida();
			if(ativo){
			checarPerguntas();
			checarMissaoHit();
			checarMaca2();
			checarColisao();
			//missao.checarMissao(resultado);
//			mover();
			cobra.mover();
			repaint();
			}
		}

	//	repaint();
	}
		
	public void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}
	
	public void setQuantidadeDePerguntas(int quantidadeDePerguntas) {
		this.quantidadeDePerguntas = quantidadeDePerguntas;
	}
	
}
