package fr.hb.verbes_irreguliers;

import java.util.Scanner;

import fr.hb.verbes_irreguliers.business.Partie;
import fr.hb.verbes_irreguliers.service.PartieService;
import fr.hb.verbes_irreguliers.service.QuestionService;
import fr.hb.verbes_irreguliers.service.VerbeService;
import fr.hb.verbes_irreguliers.service.impl.PartieServiceImpl;
import fr.hb.verbes_irreguliers.service.impl.QuestionServiceImpl;
import fr.hb.verbes_irreguliers.service.impl.VerbeServiceImpl;

public class App {
	
	static VerbeService verbeService = new VerbeServiceImpl();
	static QuestionService questionService = new QuestionServiceImpl();
	static PartieService partieService = new PartieServiceImpl();
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//bienvenue
		s("Bienvenue sur verbes irréguliers");
		//nombre de verbe dispo
		System.out.println("Nombre de verbes irréguliers disponibles : " + verbeService.getVerbes().size());
		partieService.start();
		s("Au revoir");
		

	}
	static public void s(String string)
	{
		System.out.println(string);
	}
	
}
