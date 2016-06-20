package control;

import model.Missao;
import view.Fase;
import view.TelaOpcoes;

public class Opcoes {
	private boolean soma;
	private boolean subtracao;
	private boolean multiplicacao;
	private boolean divisao;
	private boolean tudo;

	private int velocidadeBaixa = 190;
	private int velocidadeNormal = 140;
	private int velocidadeRapida = 90;
	private int quantidadeDePerguntas = 20;
	
	private boolean veloBaixa;
	private boolean veloNorma;
	private boolean veloRapida;

	public Opcoes(){
		veloBaixa = false;
		veloNorma = true;
		veloRapida = false;
	}

	public void carregarConfiguracoes(Fase fase,Missao missao){
		if(veloBaixa){
			fase.setVELOCIDADE(velocidadeBaixa);
		}
		if(veloNorma){
			fase.setVELOCIDADE(velocidadeNormal);
		}
		if(veloRapida){
			fase.setVELOCIDADE(velocidadeRapida);
		}

		/**
		 * 
		 * =================================  Carregar Operações =====================================
		 * 
		 * */

		if(soma){
			missao.setSoma(true);
			missao.setSubtracao(false);
			missao.setMultiplicacao(false);
			missao.setDivisao(false);
			missao.setTudo(false);
		}
		if(subtracao){
			missao.setSoma(false);
			missao.setSubtracao(true);
			missao.setMultiplicacao(false);
			missao.setDivisao(false);
			missao.setTudo(false);
		}
		if(multiplicacao){
			missao.setSoma(false);
			missao.setSubtracao(false);
			missao.setMultiplicacao(true);
			missao.setDivisao(false);
			missao.setTudo(false);
		}
		if(divisao){
			missao.setSoma(false);
			missao.setSubtracao(false);
			missao.setMultiplicacao(false);
			missao.setDivisao(true);
			missao.setTudo(false);
		}
		if(tudo){
			missao.setSoma(false);
			missao.setSubtracao(false);
			missao.setMultiplicacao(false);
			missao.setDivisao(false);
			missao.setTudo(true);
		}
		fase.setQuantidadeDePerguntas(quantidadeDePerguntas);
	}

	public void receberConfiguracoes(TelaOpcoes tela){

		if(tela.getVelocidadeBaixa().isSelected()){
			veloBaixa = true;
			veloNorma = false;
			veloRapida = false;
		}
		if(tela.getVelocidadeNormal().isSelected()){
			veloBaixa = false;
			veloNorma = true;
			veloRapida = false;
		}
		if(tela.getVelocidadeRapida().isSelected()){
			veloBaixa = false;
			veloNorma = false;
			veloRapida = true;
		}

		/**
		 * 
		 * ===============================================configurações de operações==============================
		 * 		  
		 * */

		if(tela.getSoma().isSelected()){
			soma = true;
			subtracao = false;
			multiplicacao = false;
			divisao = false;
			tudo = false;
		}
		if(tela.getSubtracao().isSelected()){
			soma = false;
			subtracao = true;
			multiplicacao = false;
			divisao = false;
			tudo = false;
		}
		if(tela.getMultiplicacao().isSelected()){
			soma = false;
			subtracao = false;
			multiplicacao = true;
			divisao = false;
			tudo = false;
		}
		if(tela.getDivisao().isSelected()){
			soma = false;
			subtracao = false;
			multiplicacao = false;
			divisao = true;
			tudo = false;
		}
		if(tela.getTudo().isSelected()){
			soma = false;
			subtracao = false;
			multiplicacao = false;
			divisao = false;
			tudo = true;
		}
		
		quantidadeDePerguntas = Integer.parseInt(tela.getQuantidade().getText());

	}

	/**
	 *
	 * ==============Gets e sets dos booleans===============
	 * 
	 * */


	public boolean isSoma() {
		return soma;
	}

	public void setSoma(boolean soma) {
		this.soma = soma;
	}

	public boolean isSubtracao() {
		return subtracao;
	}

	public void setSubtracao(boolean subtracao) {
		this.subtracao = subtracao;
	}

	public boolean isMultiplicacao() {
		return multiplicacao;
	}

	public void setMultiplicacao(boolean multiplicacao) {
		this.multiplicacao = multiplicacao;
	}

	public boolean isDivisao() {
		return divisao;
	}

	public void setDivisao(boolean divisao) {
		this.divisao = divisao;
	}

	public boolean isTudo() {
		return tudo;
	}

	public void setTudo(boolean tudo) {
		this.tudo = tudo;
	}

	/**
	 *
	 * ==================get e sets dos inteiros==========================
	 * 
	 * */

	public int getVelocidadeBaixa() {
		return velocidadeBaixa;
	}

	public void setVelocidadeBaixa(int velocidadeBaixa) {
		this.velocidadeBaixa = velocidadeBaixa;
	}

	public int getVelocidadeNormal() {
		return velocidadeNormal;
	}

	public void setVelocidadeNormal(int velocidadeNormal) {
		this.velocidadeNormal = velocidadeNormal;
	}

	public int getVelocidadeRapida() {
		return velocidadeRapida;
	}

	public void setVelocidadeRapida(int velocidadeRapida) {
		this.velocidadeRapida = velocidadeRapida;
	}

	public int getQuantidadeDePerguntas() {
		return quantidadeDePerguntas;
	}

	public void setQuantidadeDePerguntas(int quantidadeDePerguntas) {
		this.quantidadeDePerguntas = quantidadeDePerguntas;
	}



}
