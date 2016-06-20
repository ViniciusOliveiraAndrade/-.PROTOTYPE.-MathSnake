package model;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Missao {
	private Random gerador = new Random();
	private int a,b;
	private int resultado;
	private String operador;
	private Image imagem;
	
	private boolean soma = false;
	private boolean subtracao = false;
	private boolean multiplicacao = false;
	private boolean divisao = false;
	private boolean tudo = true;
	
	
	public Missao(){
		ImageIcon i = new ImageIcon("res/missaointem.png");
		imagem = i.getImage();
		missao();
		
		
	}

	public void missao(){
		
		if(soma){
			a=gerador.nextInt(10);
			b=gerador.nextInt(10);
			operador="+";
			resultado=a+b;
		}
		if(subtracao){
			a=gerador.nextInt(10);
			b=gerador.nextInt(10);
			operador="-";
			resultado=a-b;
		}
		if(multiplicacao){
			a=gerador.nextInt(10);
			b=gerador.nextInt(10);
			operador="*";
			resultado=a*b;
		}
		if(divisao){
			a=gerador.nextInt(10);
			b=gerador.nextInt(10);
			operador="/";
			if(a>b){
				if(a==0){a++;}
				if(b==0){b++;}
				a=a*b;
				resultado = a/b;
			}if(a<b){
				if(a==0){a++;}
				if(b==0){b++;}
				b=a*b;
				resultado = b/a;
			}
			
		}
		
		if(tudo){
			int i = gerador.nextInt(4);
		
		switch(i){
		case 0:{
			a=gerador.nextInt(10);
			b=gerador.nextInt(10);
			operador="+";
			resultado=a+b;
			break;
		}
		case 1:{
			a=gerador.nextInt(10);
			b=gerador.nextInt(10);
			operador="-";
			resultado=a-b;
			break;
		}
		case 2:{
			a=gerador.nextInt(10);
			b=gerador.nextInt(10);
			operador="*";
			resultado=a*b;
			break;
		}
		case 3:{
			a=gerador.nextInt(10);
			b=gerador.nextInt(10);
			operador="/";
			if(a>b){
				if(a==0){a++;}
				if(b==0){b++;}
				a=a*b;
				resultado = a/b;
			}if(a<b){
				if(a==0){a++;}
				if(b==0){b++;}
				b=a*b;
				resultado = b/a;
			}if(a==b){
				if(a==0){a++;}
				if(b==0){b++;}
				resultado = a/b;
			}
			break;
		}
		}
		}

	}

	public boolean checarMissao(int resultado){
		boolean a = true;
		if(this.resultado==resultado){
			a = true;
		}
		if(this.resultado!=resultado){
			a = false;
		}
		return a;
	}


/**
 * 
 * ==============================================Geters e Seters========================
 * 
 * */
	
	
	
	public Image getImagem() {
		return imagem;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getResultado() {
		return resultado;
	}

	public String getOperador() {
		return operador;
	}

	public void setSoma(boolean soma) {
		this.soma = soma;
	}

	public void setSubtracao(boolean subtracao) {
		this.subtracao = subtracao;
	}

	public void setMultiplicacao(boolean multiplicacao) {
		this.multiplicacao = multiplicacao;
	}

	public void setDivisao(boolean divisao) {
		this.divisao = divisao;
	}

	public void setTudo(boolean tudo) {
		this.tudo = tudo;
	}

	
	
}
