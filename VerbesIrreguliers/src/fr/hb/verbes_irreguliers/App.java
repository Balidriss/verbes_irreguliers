package fr.hb.verbes_irreguliers;

import java.util.Scanner;

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
		singletonize();

		s("Bienvenue sur verbes irréguliers");

		System.out.println("Nombre de verbes irréguliers disponibles : " + verbeService.getVerbes().size());

		do {

			s("Nouvelle partie !");

			partieService.askQtyQuestions();

			s("Merci de séparer le prétérit et le participe passé avec une virgule");

			partieService.askQuestions();

			// resultat final // AVGdeltaTime // log display.
			partieService.recap();

		} while (partieService.askNew());
		s("Au revoir");

	}

	private static void singletonize() {
		partieService.setServices(verbeService, questionService, sc);
		questionService.setServices(verbeService, partieService, sc);
		verbeService.setServices(partieService, questionService, sc);
	}

	static public void s(String string) {
		System.out.println(string);
	}

}
