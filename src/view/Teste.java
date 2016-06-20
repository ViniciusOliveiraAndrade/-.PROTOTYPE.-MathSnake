package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teste implements KeyListener{

	
	public String nome;
	public void escrever(){
		System.out.println(nome);
	}
	
	public void keyPressed(KeyEvent tecla) {

		int code = tecla.getKeyCode();
		
		if(code == KeyEvent.VK_UP){
			nome=("cima");
		}
		if(code == KeyEvent.VK_DOWN){
			nome=("baixo");
		}
		if(code == KeyEvent.VK_LEFT){
			nome=("esquerda");
		}
		if(code == KeyEvent.VK_RIGHT){
			nome=("direita");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
