package model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Toolkit;

public class Som {
	
	private AudioClip menu;
	private AudioClip jogo;
	private AudioClip acerto;
	private AudioClip botao;
	
	public Som(){
	menu =  Applet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/model/menu.wav"));
	jogo = Applet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/model/jogo.wav"));
	acerto = Applet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/model/comendo.wav"));
	botao = Applet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/model/butoes.wav"));
	}
	
	public void menuIniciar(){menu.loop();}
	public void menuParar(){menu.stop();}
	public void jogoIniciar(){jogo.loop();}
	public void jogoParar(){jogo.stop();}
	public void botaoInicius(){botao.play();}
	public void acertoIniciar(){acerto.play();}
	
	}
