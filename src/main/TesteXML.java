package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.Player;
import model.Ranking;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.ClassLoaderReference;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TesteXML {

	public static void main(String[] args) {
		
		ArrayList<Player> players = new ArrayList<>();
		players.add(new Player());
		Ranking ranking = new Ranking();
//		ranking.addNoRanking(players);
//		ranking.addNoRanking(new Player("teste 2"));
//		
		XStream xstream = new XStream(new DomDriver());
		xstream.autodetectAnnotations(true);
//		//xstream.alias("Pessoa", Player.class);
		String xml = xstream.toXML(ranking);
		System.out.println(xml);
		gerarXML(xml);
		buscarXML();
	}
	
	
	public static void buscarXML(){
		try {
			FileReader ler = new FileReader("resource/ranking.xml");
		
			XStream xstream = new XStream(new DomDriver());
			
			xstream.alias("ranking", Ranking.class);
			xstream.alias("Aluno",Player.class);
			Ranking ranking = (Ranking) xstream.fromXML(ler);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
		public static void gerarXML(String xml){
			PrintWriter print = null;
			
			try {
				File file = new File("resource/ranking.xml");
				print = new PrintWriter(file);
				print.write(xml);
				print.flush();
				print.close();
				System.out.println(ClassLoaderReference.class.getName());
				
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
}
