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
import model.MenuMult;
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

	private Image fundo;

	private int missaoX[] = new int [4];
	private int missaoY[] = new int [4];


	private int maissaoX,maissaoY;

	private int aparencia=0;
	private int quantidadeDePerguntas; 
	private int missaoResultado;

	private boolean ativo = true;
	private boolean multP = false;

	private JFrame j;
	private PanelMenu menuzinho = new PanelMenu();
	private MenuMult menuMult = new MenuMult();
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
	private Snake cobraJogados1;
	private Snake cobraJogados2;

	public Fase (){
		audio = new Som();
		audio.jogoIniciar();

		fundo = new ImageIcon("res/FaseFundo.png").getImage();

		setFocusable(true);
		setDoubleBuffered(true);

		addKeyListener(new AdaptadorTeclado());

		multP = Main.opcoes.isMultP();

		iniciarGame();

		missao = new Missao();

		Main.opcoes.carregarConfiguracoes(this, missao);


		maca2 = new Maca();
		maca2.start();
		maca2.hide();

		missaoErrada1 = new Missao();
		missaoErrada2 = new Missao();
		missaoErrada3 = new Missao();
		missaoErrada4 = new Missao();

		ranking = new Ranking();
		escreve = new EscreverXML(ranking);
		ranking.setRanking(escreve.buscarXML());

	}
	private void iniciarGame() {
		cobraJogados1 = new Snake(TAMANHOMATRIZ, 0, 0, "direita");

		if(multP){
			cobraJogados2 = new Snake(TAMANHOMATRIZ, 580, 560 , "esquerda");
		}
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

		if(multP){
			menuMult.menudesenhar(g, cobraJogados1.getScore(), cobraJogados2.getScore(), cobraJogados1.getVida(), cobraJogados2.getVida(), missao);
		}else{menuzinho.menudesenhar(g,cobraJogados1.getScore(),cobraJogados1.getVida(),missao);}
		sprite();

	}

	private void doDrawing(Graphics g) {

		if (ativo) {
			g.drawImage(maca2.getImagem(), maca2.getX(), maca2.getY(), this);
			cobraJogados1.desenharCobra(g);

			if(multP){cobraJogados2.desenharCobra(g);}
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

		if ((cobraJogados1.getCabeca().x  == maissaoX) && (cobraJogados1.getCabeca().y == maissaoY)) {
			missaoResultado=missao.getResultado();
			audio.acertoIniciar();

			if(missao.checarMissao(missaoResultado)){
				cobraJogados1.setScore(cobraJogados1.getScore()+1);
				cobraJogados1.addCorpo();
				Main.player.receberAcertos(missao,missaoResultado);
				geradores();
			}
		}
		if(multP){
			if ((cobraJogados2.getCabeca().x  == maissaoX) && (cobraJogados2.getCabeca().y == maissaoY)) {
				missaoResultado=missao.getResultado();
				audio.acertoIniciar();

				if(missao.checarMissao(missaoResultado)){
					cobraJogados2.setScore(cobraJogados2.getScore()+1);
					cobraJogados2.addCorpo();
					Main.player.receberAcertos(missao,missaoResultado);
					geradores();
				}
			}
		}
		checarMissaoHitErrada(cobraJogados1);
		if(multP){checarMissaoHitErrada(cobraJogados2);}
	}

	private void checarMissaoHitErrada(Snake s) {
		for (int i = 0; i<=3;i++){
			if ((s.getCabeca().x  == missaoX[i]) && (s.getCabeca().y == missaoY[i])) {
				missaoResultado=missaoErrada1.getResultado();
				audio.acertoIniciar();
				if(missao.checarMissao(missaoResultado)){
					s.setScore(s.getScore()+1);
					s.addCorpo();
					Main.player.receberAcertos(missao,missaoResultado);
					geradores();
				}else{
					s.setScore(s.getScore()-1);
					s.retiraCorpo();
					s.menosVida();
					Main.player.receberErros(missao,missaoResultado);
					geradores();

				}
			}
		}
	}
	public void geradores(){
		gerarMissao();
		quantidadeDePerguntas --;
		gerarMissaoLocal();
		gerarMissaoErradaLocal();}
	private void checarColisao() {

		if ((cobraJogados1.getCabeca().y >= ALTURA)||(cobraJogados1.getCabeca().y < 0)) {
			cobraJogados1.menosVida();
			checarVida();
			cobraJogados1.recomecar();
		}

		if ((cobraJogados1.getCabeca().x >= LARGURA)||(cobraJogados1.getCabeca().x < 0)) {
			cobraJogados1.menosVida();
			checarVida();
			cobraJogados1.recomecar();
		}
		if(multP){if ((cobraJogados2.getCabeca().y >= ALTURA)||(cobraJogados2.getCabeca().y < 0)) {
			cobraJogados2.menosVida();
			checarVida();
			cobraJogados2.recomecar();
		}

		if ((cobraJogados2.getCabeca().x >= LARGURA)||(cobraJogados2.getCabeca().x < 0)) {
			cobraJogados2.menosVida();
			checarVida();
			cobraJogados2.recomecar();
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

		if ((cobraJogados1.getCabeca().x  == maca2.getX()) && (cobraJogados1.getCabeca().y == maca2.getY())) {
			audio.acertoIniciar();
			if(cobraJogados1.getVida()<=2){
				cobraJogados1.maisVida();}
			maca2.setAtivo(false);
			maca2.hide();

		}
		if(multP){if ((cobraJogados2.getCabeca().x  == maca2.getX()) && (cobraJogados2.getCabeca().y == maca2.getY())) {
			audio.acertoIniciar();
			if(cobraJogados2.getVida()<=2){
				cobraJogados2.maisVida();}
			maca2.setAtivo(false);
			maca2.hide();
		}
		}
	}

	private void checarVida(){
		if(cobraJogados1.getVida()<=0){
			ativo=false;
			audio.jogoParar();
			missaoResultado=missao.getResultado();
			maca2.hide();
			recomecar();
		}
		if(multP){if(cobraJogados2.getVida()<=0){
			ativo=false;
			audio.jogoParar();
			missaoResultado=missao.getResultado();
			maca2.hide();
			recomecar();
		}
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
		j.setLocationRelativeTo(null);
		j.setResizable(false);
		j.add(recomecar);
		j.add(sair);
		j.setLayout(new FlowLayout());
		recomecar.addActionListener(this);
		sair.addActionListener(this);
		j.setVisible(true);
	}

	private void sprite(){
		this.aparencia++;
		if(this.aparencia>=5){
			this.aparencia=0;
		}
		if(multP){menuMult.setAparencia(aparencia);}else{menuzinho.setAparencia(this.aparencia);}
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

			if ((key == KeyEvent.VK_LEFT) && (!cobraJogados1.isDireita())) {
				cobraJogados1.lado("esquerda");
			}

			if ((key == KeyEvent.VK_RIGHT) && (!cobraJogados1.isEsquerda())) {
				cobraJogados1.lado("direita");
			}

			if ((key == KeyEvent.VK_UP) && (!cobraJogados1.isBaixo())) {
				cobraJogados1.lado("cima");
			}

			if ((key == KeyEvent.VK_DOWN) && (!cobraJogados1.isCima())) {
				cobraJogados1.lado("baixo");
			}

			if (key == KeyEvent.VK_P){
				if(ativo){
					ativo=false;
				}else{
					ativo=true;
				}
			}
			if(multP){
				if ((key == KeyEvent.VK_A) && (!cobraJogados2.isDireita())) {
					cobraJogados2.lado("esquerda");
				}

				if ((key == KeyEvent.VK_D) && (!cobraJogados2.isEsquerda())) {
					cobraJogados2.lado("direita");
				}

				if ((key == KeyEvent.VK_W) && (!cobraJogados2.isBaixo())) {
					cobraJogados2.lado("cima");
				}

				if ((key == KeyEvent.VK_S) && (!cobraJogados2.isCima())) {
					cobraJogados2.lado("baixo");
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==recomecar){

			cobraJogados1.recomecar();
			if(multP){
				cobraJogados2.recomecar();}
			escreve.gerarXML();
			audio.jogoIniciar();
			cobraJogados1.setScore(0);
			if(multP){cobraJogados2.setScore(0);}
			ativo=true;
			cobraJogados1.setTamanhoCobra(3);
			if(multP){cobraJogados2.setTamanhoCobra(3);}
			quantidadeDePerguntas=Main.opcoes.getQuantidadeDePerguntas();
			Main.player.recomecar();
			j.dispose();
			timer.start();
			cobraJogados1.setVida(3);
			if(multP){cobraJogados2.setVida(3);}
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
				cobraJogados1.mover();
				if(multP){cobraJogados2.mover();}
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
