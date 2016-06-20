package model;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Maca extends Thread{
	private final int LARGURA = 625;
	private final int ALTURA = 560;
	private int x=100,y=100;
	private Image imagem;
	private Random gerador = new Random();
	private boolean ativo=false;
	private int i = 0;
	public Maca(){
			ImageIcon iia = new ImageIcon("res/MAÇA.png");
			imagem = iia.getImage();
		
	}
	public void run(){
		while(true){
		try {
			Thread.sleep(30000);
			i++;
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}
		if(i>0){ativo=true;
	    gerarMacaLocal();
		}
		}
	}
	
	public void desenhoManca(Graphics g){
		g.drawImage(imagem, x, y, null);
	}
	
	
	public void gerarMacaLocal() {

		int r = gerador.nextInt(32);
		x = ((r * 20));

		r = gerador.nextInt(32);
		y = ((r * 20));
		
		if(ativo){
		
		if((x<0)||(x>=LARGURA)){
			gerarMacaLocal();
		}
		if((y<0)||(y>=ALTURA)){
			gerarMacaLocal();
		}
		}
	}
	
//	private void checarMaca(int x[],int y[],int score) {
//
//		if ((x[0] == this.x) && (y[0] == this.y)) {
//			score+=1;
//			tamanhoCobra++;
//			gerarMacaLocal();
//		}
//	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}
	
	
	
}
