package model;

import java.applet.Applet;
import java.applet.AudioClip;

public class Som {
	
	private AudioClip menu;
	private AudioClip jogo;
	private AudioClip acerto;
	private AudioClip botao;
	
	public Som(){
	menu =  Applet.newAudioClip(this.getClass().getResource("menu.wav"));
	jogo = Applet.newAudioClip(this.getClass().getResource("jogo.wav"));
	acerto = Applet.newAudioClip(this.getClass().getResource("comendo.wav"));
	botao = Applet.newAudioClip(this.getClass().getResource("butoes.wav"));
	}
	
	public void menuIniciar(){menu.loop();}
	public void menuParar(){menu.stop();}
	public void jogoIniciar(){jogo.loop();}
	public void jogoParar(){jogo.stop();}
	public void botaoInicius(){botao.play();}
	public void acertoIniciar(){acerto.play();}
	
	}
