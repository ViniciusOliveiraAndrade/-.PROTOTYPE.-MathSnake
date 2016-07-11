package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Snake {
	private Image cabeca;
	private Image corpo;
	
	private int tamanhoCobra = 3;
	
	private boolean esquerda = false;
	private boolean direita = false;
	private boolean cima = false;
	private boolean baixo = false;
	private int xInicial, yInicial;
	private final int x[];
	private final int y[];
	
	public Snake(int TAMANHOMATRIZ, int x, int y, String lado) {
		
		this.x= new int[TAMANHOMATRIZ];
		this.y = new int[TAMANHOMATRIZ];
		xInicial = x; 
		yInicial=y;
		this.lado(lado);
		criaCorpo(); 
	
	}
	public void criaCorpo(){
		for (int z = 0; z < tamanhoCobra; z++) {
			this.x[z] = xInicial - z * 20;
			this.y[z] = yInicial;
		}
	}
	public void lado(String lado){
		switch (lado){
		case "cima":{
			esquerda = false;
			direita = false;
			cima = true;
			baixo = false;
			break;
		}
		case "baixo":{
			esquerda = false;
			direita = false;
			cima = false;
			baixo = true;
			break;
		}
		case "direita":{
			esquerda = false;
			direita = true;
			cima = false;
			baixo = false;
			break;
		}
		case "esquerda":{
			esquerda = true;
			direita = false;
			cima = false;
			baixo = false;
			break;
		}
		
		}
	}
	private void carregarImagens() {
		if(cima){
			cabeca = new ImageIcon("res/cabecaNorte.png").getImage();
		}
		if(esquerda){
			cabeca = new ImageIcon("res/cabecaOeste.png").getImage();
		}
		if(direita){
			cabeca = new ImageIcon("res/cabecaLeste.png").getImage();
		}	
		if(baixo){
			cabeca = new ImageIcon("res/cabecaSul.png").getImage();
		}
		
		if(cima){
			corpo = new ImageIcon("res/corpoNorte.png").getImage();
		}
		if(esquerda){
			corpo = new ImageIcon("res/corpoOeste.png").getImage();
		}
		if(direita){
			corpo = new ImageIcon("res/corpoLeste.png").getImage();
		}	
		if(baixo){
			corpo = new ImageIcon("res/corpoSul.png").getImage();
		}
	}
	public void desenharCobra(Graphics g){
		carregarImagens();
		
		for (int z = 0; z < tamanhoCobra; z++) {
			if (z == 0) {
				g.drawImage(cabeca, x[z], y[z], null);
			} else {
				g.drawImage(corpo, x[z], y[z], null);
			}
		}
	}
	public void addCorpo(){
		tamanhoCobra++;
	}
	public void retiraCorpo(){
		tamanhoCobra--;
	}
	public Point getCabeca(){
		Point p = new Point(x[0], y[0]);
		return p;
	}
	public void recomecar(){
		
	}
	public void mover() {

		for (int z = tamanhoCobra; z > 0; z--) {
			x[z] = x[(z - 1)];
			y[z] = y[(z - 1)];
		}

		if (esquerda) {
			x[0] -= 20;
		}

		if (direita) {
			x[0] += 20;
		}

		if (cima) {
			y[0] -= 20;
		}

		if (baixo) {
			y[0] += 20;
		}
	}
}
