package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.Player;
import model.Ranking;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class EscreverXML {



	XStream xstream = new XStream(new DomDriver());;
	Ranking ranking;
	public EscreverXML(Ranking ranking){
		this.ranking=ranking;
		xstream.autodetectAnnotations(true);
	}


	public ArrayList<Player> buscarXML(){
		Ranking ranking = new Ranking();
		try {
			FileReader ler = new FileReader("resource/ranking.xml");
			
			XStream xstream = new XStream(new DomDriver());
			
			xstream.alias("ranking", Ranking.class);
			xstream.alias("Aluno",Player.class);
			Ranking ranking1 = (Ranking) xstream.fromXML(ler);
			ranking=ranking1;
		} catch (FileNotFoundException e) {System.out.println("Arquivo XML não encontrado");}
		
		return ranking.getRanking();
	}

	public void gerarXML(){
		
		String xml; 
		
		xml= xstream.toXML(ranking);
		
		PrintWriter print = null;

		try {
			File file = new File("resource/ranking.xml");

			print = new PrintWriter(file);

			print.write(xml);

			print.flush();

			print.close();

		}
		catch (FileNotFoundException e) {System.out.println("Arquivo XML não encontrado");}
	}


	
}
