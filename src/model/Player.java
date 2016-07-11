package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import main.Main;

@XStreamAlias("Aluno")
public class Player implements Comparable<Player>{

	private String nome;
	private int a;
	private int i=0;
	private int j=0;
	private int d=0;
	private int g=0;
	private int quantidadeDeAcertos=0;
	private int quantidadeDeErros=0;
	private int acertosA[];
	private int acertosB[];
	private int errosA[];
	private int errosB[] ;
	private int resultadoA[];
	private int resultadoE[];
	private int resultado;
	private String operadoreAcerto[] ;
	private String operadoreErros[] ;
	private String operador;

	public Player(){

	}

	public Player(String nome){
		this.nome=nome;
	}

	public void carregarQuantidade(){
		a=Main.opcoes.getQuantidadeDePerguntas();
		acertosA = new int[a];
		acertosB = new int[a];
		errosA = new int[a];
		errosB = new int[a];
		operadoreAcerto = new String[a];
		operadoreErros = new String[a];	
		resultadoA = new int[a];
		resultadoE = new int[a];

	}

	public void receberAcerto(Missao missao,int resultado ){
		d=missao.getA();
		operador =missao.getOperador();
		g=missao.getB();
		this.resultado = resultado;

	}

	public void receberErro(Missao missao,int resultado ){

		d=missao.getA();
		operador =missao.getOperador();
		g=missao.getB();
		this.resultado = resultado;

	}

	public void addAcerto(){
		acertosA[i]=d;
		operadoreAcerto[i]=operador;
		acertosB[i]=g;
		resultadoA[i]=resultado;
		i++;
		quantidadeDeAcertos++;
	}

	public void addErro(){
		errosA[j]=d;
		operadoreErros[j]=operador;
		errosB[j]=g;
		resultadoE[j]=resultado;
		j++;
		quantidadeDeErros++;
	}

	public void desempenho(){
		System.out.println("Aluno:"+nome+"  Quantidade de Perguntas/Acertos: "+a+" / "+quantidadeDeAcertos);

		if(quantidadeDeAcertos>0){
			System.out.println("Questoes acertadas:");
			for(int z = 0;z<quantidadeDeAcertos;z++){
				System.out.println(acertosA[z]+" "+operadoreAcerto[z]+" "+acertosB[z]+" = "+resultadoA[z]);
			}}
		if(quantidadeDeErros>0){
			System.out.println("Questoes erradas:");
			for(int z = 0;z<quantidadeDeErros;z++){
				System.out.println(errosA[z]+" "+operadoreErros[z]+" "+errosB[z]+" = "+resultadoE[z]);
			}}
	}

	public void recomecar(){
		for(int t=0;t<a;t++){
			i=0;
			j=0;
			quantidadeDeAcertos=0;
			quantidadeDeErros=0;

		}

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int compareTo(Player o) {

		if (this.quantidadeDeAcertos < o.quantidadeDeAcertos) {
			return -1;
		}
		if (this.quantidadeDeAcertos > o.quantidadeDeAcertos) {
			return 1;
		}	
		return 0;
	}

	public void receberAcertos(Missao missao,int resultado ){
		receberAcerto(missao,resultado ); 
		addAcerto();

	}
	public void receberErros(Missao missao,int resultado ){
		receberErro(missao,resultado );
		addErro();

	}

}
