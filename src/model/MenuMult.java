package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import main.Main;

public class MenuMult {
	private static final Font FONT = new Font("MV Boli", Font.BOLD, 20);		
	static Life life;
	private int aparencia;
	
	public void menudesenhar(Graphics g,int score,int score2, int vida,int vida2, Missao missao) {
		try{
			life = new Life(710 ,35);

		}catch (IOException e) {
			e.printStackTrace();
			System.out.println("Não foi possível carregar a Sprite");
		}

		g.setFont(FONT);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Score: "+score, 650, 30);
		g.drawString("Vida: ", 650, 55);

		desenharVida(vida,aparencia,g);

		g.drawString("Jogador: "+Main.player.getNome(), 650, 80);
		if(missao.getA()>missao.getB()){g.drawString("Quanto é: "+missao.getA()+""+missao.getOperador()+""+missao.getB()+"=?", 650, 155);}
		if(missao.getB()>missao.getA()){
			if(missao.getOperador()!="-"){
				g.drawString("Quanto é: "+missao.getB()+""+missao.getOperador()+""+missao.getA()+"=?", 650, 155);}
			if(missao.getOperador()=="-"){
				g.drawString("Quanto é: "+missao.getA()+""+missao.getOperador()+""+missao.getB()+"=?", 650, 155);
			}
		}
		if(missao.getA()==missao.getB()){g.drawString("Quanto é: "+missao.getA()+""+missao.getOperador()+""+missao.getB()+"=?", 650, 155);}
		
		////////////////////////////////////////////////////////////////////
		
		g.drawString("Score: "+score2, 650, 210);
		g.drawString("Vida: ", 650, 235);

		desenharVida2(vida2,aparencia,g);
		
		g.drawString("Jogador: "+Main.player.getNome(), 650, 260);
	
	}

	public void setAparencia(int aparencia) {
		this.aparencia = aparencia;
	}

	public void desenharVida2(int vida,int aparencia, Graphics g){
		
		if (vida==3){
			g.drawImage(life.sprites[aparencia], life.posX, life.posY+180, null);
			g.drawImage(life.sprites[aparencia], life.posX+30, life.posY+180, null);
			g.drawImage(life.sprites[aparencia], life.posX+60, life.posY+180, null);
		}
		if (vida==2){
			g.drawImage(life.sprites[aparencia], life.posX, life.posY+180, null);
			g.drawImage(life.sprites[aparencia], life.posX+30, life.posY+180, null);
		}
		if (vida==1){
			g.drawImage(life.sprites[aparencia], life.posX, life.posY+180, null);
		}
	
	}
public void desenharVida(int vida,int aparencia, Graphics g){
		
		if (vida==3){
			g.drawImage(life.sprites[aparencia], life.posX, life.posY, null);
			g.drawImage(life.sprites[aparencia], life.posX+30, life.posY, null);
			g.drawImage(life.sprites[aparencia], life.posX+60, life.posY, null);
		}
		if (vida==2){
			g.drawImage(life.sprites[aparencia], life.posX, life.posY, null);
			g.drawImage(life.sprites[aparencia], life.posX+30, life.posY, null);
		}
		if (vida==1){
			g.drawImage(life.sprites[aparencia], life.posX, life.posY, null);
		}
	
	}
}
